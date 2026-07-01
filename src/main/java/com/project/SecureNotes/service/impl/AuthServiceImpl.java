package com.project.SecureNotes.service.impl;

import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.Role;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterRequest request) {
        User user = new User();
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // entity list, not strings

    }

    @Override
    public String deleteUserById(UUID id){

        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with ID: " + id));


        String name = user.getName();
        userRepository.delete(user);

        return name;

    }
}
