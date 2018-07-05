/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.security;

import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.UsersRepository;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    @Autowired
    private UsersRepository appUserRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginObj loginObj, HttpServletResponse response) throws IOException {
        String token = null;
        Users appUser = appUserRepository.findByEmailAndIsDeletedIsFalse(loginObj.getUsername());

        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        String password = loginObj.getPassword();
        loginObj.setPassword(passwordEncoder.encodePassword(password, null));

        Map<String, Object> tokenMap = new HashMap<>();
        if (appUser != null && appUser.getPassword().equals(loginObj.getPassword())) {
            token = Jwts.builder().setSubject(loginObj.getUsername()).claim("roles", appUser.getSystemRolesSet()).setIssuedAt(new Date())
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            appUser.setPassword("PROTECTED");
            tokenMap.put("user", appUser);
            return new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("error", "invalid email/password");
            return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler(value = {io.jsonwebtoken.SignatureException.class, io.jsonwebtoken.MalformedJwtException.class})
    public String invalidToken() {
        return "invalid Token";
    }
}
