package com.construction.service.construction.controller;

import com.construction.service.construction.model.security.User;
import com.construction.service.construction.repository.security.UserRepository;
import com.construction.service.construction.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User userRequest) {
        var username = userRequest.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(username,user.getRoles());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}