package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.*;
import com.ecommerce.user_service.service.AuthService;
import com.ecommerce.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest req) {

        // (skip DB check for now — assume valid user)
        return jwtUtil.generateToken(req.getUsername());
    }
}
