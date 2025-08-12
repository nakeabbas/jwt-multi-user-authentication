package com.example.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userEndpoint() {
        logger.info("Process Start User Role :" ,System.currentTimeMillis());
        return "Hello User!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminEndpoint() {
        logger.info("Process Start Admin Role :" ,System.currentTimeMillis());
        return "Hello Admin!";
    }
}