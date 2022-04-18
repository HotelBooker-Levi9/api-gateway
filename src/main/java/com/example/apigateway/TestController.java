package com.example.apigateway;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);

        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @GetMapping("/check")
    public String hello() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

}
