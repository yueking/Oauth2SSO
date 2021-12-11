package com.yueking.core.controller;


import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/users")
public class UserController {
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        // return authentication.getPrincipal();
        String header = request.getHeader("Authorization");
        System.out.println("header:"+header);
        String token = header.substring(header.lastIndexOf("bearer") + 7);
        return Jwts.parser().setSigningKey("myJwtKey".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }
}
