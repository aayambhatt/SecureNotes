package com.project.SecureNotes.service.impl;

import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.Role;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

    }

    @Override
    public String getAllUsers(){
        List<User> allUsers = userRepository.findAll();
        return allUsers.toString();
    }
}
