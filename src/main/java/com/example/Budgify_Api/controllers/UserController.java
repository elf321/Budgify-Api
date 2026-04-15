package com.example.Budgify_Api.controllers;


import com.example.Budgify_Api.services.user.UserService;
import com.example.Budgify_Api.services.user.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO.UserResponse> register (@RequestBody UserDTO.UserRegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO.UserResponse> login (@RequestBody UserDTO.UserLoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO.UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
