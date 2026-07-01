package com.project.SecureNotes.controller;


import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.dto.UserResponse;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.mapper.UserMapper;
import com.project.SecureNotes.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        User savedUser = authService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toResponse(savedUser));
    }

    @GetMapping("/auth/getUsers")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = authService.getAllUsers();
        List<UserResponse> response = users.stream()
                .map(userMapper::toResponse) // map each entity → DTO
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/auth/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
      String deletedUser =  authService.deleteUserById(id);
        return ResponseEntity.ok("User " + deletedUser + " deleted from DB");
    }


}
