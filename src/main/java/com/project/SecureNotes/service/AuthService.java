package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.User;

import java.util.List;

public interface AuthService {
    public User registerUser(RegisterRequest registerRequest);
    public List<User> getAllUsers();
}
