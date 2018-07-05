/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.service.UsersService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Client 1
 */
@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/registerUser")
    public Map<String, Object> registerUser(@RequestBody Users body) {
        return usersService.registerUser(
                body.getFirstName(),
                body.getLastName(),
                body.getEmail(),
                body.getMobileToken(),
                body.getMobile(),
                body.getPassword(),
                body.getUserLanguage()
        );
    }

    @RequestMapping("/getUserInfo")
    public GeneralResponse<Users> getUserInfo(@RequestBody GeneralParameter body, Principal principal) {
        return usersService.getUserInfo(
                body.getLanguage().toString(),
                principal.getName()
        );
    }

    @PostMapping("/add-admin")
    public GeneralResponse<Map<String, Object>> addAdmin(@RequestBody Users body) {
        return usersService.addAdmin(
                body.getFirstName(),
                body.getLastName(),
                body.getEmail(),
                body.getMobile(),
                body.getPassword(),
                body.getUserLanguage()
        );
    }

    @PostMapping("/get-all-admins")
    public GeneralResponse<List<Users>> findAllAdmins(@RequestBody GeneralParameter body) {
        return usersService.findAllAdmins(body.getPage(), body.getNoOfRows());
    }

    @PostMapping("/get-all-users")
    public GeneralResponse<List<Users>> findAllUsers(@RequestBody GeneralParameter body) {
        return usersService.findAllUsers(body.getPage(), body.getNoOfRows());
    }

    @PostMapping("/updateUser")
    public GeneralResponse updateUser(@RequestBody Users user, Principal principal) {
        return usersService.updateUser(user.getEmail(), user);
    }

    @GetMapping("/findUserById/{userId}")
    public GeneralResponse<Users> findUserById(@PathVariable("userId") long userId) {
        return usersService.findByUserId(userId);
    }

    @PostMapping("/changePassword")
    public GeneralResponse changePassword(@RequestBody GeneralParameter body, Principal principal) {
        System.out.println(body.getOldPassword());
        System.out.println(usersService.changePassword(principal.getName(), body.getOldPassword(), body.getNewPassword()).getStatusCode());
        return usersService.changePassword(principal.getName(), body.getOldPassword(), body.getNewPassword());
    }

    @PostMapping(value = "/changeProfilePicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse changeProfilePicture(@RequestBody MultipartFile profilePic, Principal principal) {
        return usersService.changeProfilePicture(principal.getName(), profilePic);
    }

    @GetMapping("/deleteUser/{id}")
    public GeneralResponse deleteUser(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }

    @PostMapping("/socialLogin")
    public Map<String, Object> socialLogin(@RequestBody GeneralParameter body) {
        switch (body.getType().toUpperCase()) {
            case "GOOGLE":
                return usersService.loginGoogle(body.getToken());
            case "FACEBOOK":
                return usersService.loginFaceBook(body.getToken());
            default:
                throw new AssertionError();
        }

    }

}
