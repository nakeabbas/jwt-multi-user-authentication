package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userEndpoint() {
        return "Hello User!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminEndpoint() {
        return "Hello Admin!";
    }
}