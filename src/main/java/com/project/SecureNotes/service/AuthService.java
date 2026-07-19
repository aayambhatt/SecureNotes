package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.LoginRequest;
import com.project.SecureNotes.dto.LoginResponse;
import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.dto.UpdateRequest;
import com.project.SecureNotes.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface AuthService {
     User registerUser(RegisterRequest registerRequest);
     List<User> getAllUsers();
     String deleteUserById(UUID id, UserDetails userDetails);
     LoginResponse loginUser(LoginRequest loginRequest);
     String updateUser(UUID id, UpdateRequest updateRequest);
}
