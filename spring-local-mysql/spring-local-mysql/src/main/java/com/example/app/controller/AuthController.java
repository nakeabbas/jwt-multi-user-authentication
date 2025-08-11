package com.example.app.controller;

import com.example.app.entities.AuthorizedUser;
import com.example.app.entities.Role;
import com.example.app.jwt.JWTUtills;
import com.example.app.repo.AuthenticatedUser;
import com.example.app.repo.UserRole;
import com.example.app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired(required = true)
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JWTUtills jwtUtil;

    @Autowired
     PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticatedUser userRepository;

    @Autowired
    UserRole roleRepository;

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        AuthorizedUser user = new AuthorizedUser();
        user.setUsername(request.get("username"));
        user.setPassword(passwordEncoder.encode(request.get("password")));
        Role role = roleRepository.findByName(request.get("role"))
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.get("username"));
        return jwtUtil.generateToken(userDetails);
    }
}
