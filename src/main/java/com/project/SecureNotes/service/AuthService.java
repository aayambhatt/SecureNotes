package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.User;

import java.util.List;

public interface AuthService {
     User registerUser(RegisterRequest registerRequest);
     List<User> getAllUsers();
     void deleteUserByEmail(String email);
}
