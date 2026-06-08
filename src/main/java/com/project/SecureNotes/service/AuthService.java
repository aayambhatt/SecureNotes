package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.RegisterRequest;

public interface AuthService {
    public void registerUser(RegisterRequest registerRequest);
    public String getAllUsers();
}
