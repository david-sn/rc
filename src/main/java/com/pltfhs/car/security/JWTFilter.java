/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.security;

import com.pltfhs.car.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author Client 1
 */
public class JWTFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "roles";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("***************>  " + request.getRequestURI());
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
        } else {
            try {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
                filterChain.doFilter(req, res);
            } catch (io.jsonwebtoken.SignatureException ex) {
                res.getOutputStream().write("invalid Token".getBytes());
            }
        }
    }

    public String getUserFromToken(String headerToken) {
        if (headerToken != null) {

            String token = headerToken.substring(7);
            Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
            return claims.getSubject();
        }
        return null;
    }

    /**
     * Method for creating Authentication for Spring Security Context Holder
     * from JWT claims
     *
     * @param claims
     * @return
     */
    public Authentication getAuthentication(Claims claims) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<?> roles = (List<?>) claims.get(AUTHORITIES_KEY);
        for (Object role : roles) {
            LinkedHashMap value = (LinkedHashMap) role;
            authorities.add(new SimpleGrantedAuthority(value.get("roleName").toString()));
        }
        Users principal = new Users(claims.getSubject());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), "", authorities);
        return usernamePasswordAuthenticationToken;
    }
}
