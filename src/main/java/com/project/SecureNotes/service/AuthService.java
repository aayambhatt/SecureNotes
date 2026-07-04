package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.LoginRequest;
import com.project.SecureNotes.dto.LoginResponse;
import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.User;

import java.util.List;
import java.util.UUID;

public interface AuthService {
     User registerUser(RegisterRequest registerRequest);
     List<User> getAllUsers();
     String deleteUserById(UUID id);
     LoginResponse loginUser(LoginRequest loginRequest);
}
