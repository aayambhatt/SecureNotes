package com.project.SecureNotes.service.impl;

import com.project.SecureNotes.dto.LoginRequest;
import com.project.SecureNotes.dto.LoginResponse;
import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.dto.UpdateRequest;
import com.project.SecureNotes.entity.Role;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.AuthService;
import com.project.SecureNotes.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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
    public LoginResponse loginUser(LoginRequest loginRequest){
        // fetch the user
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid Credentials"));

        // match password
       if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
           throw new RuntimeException("Invalid password");
       }


       LoginResponse loginResponse = new LoginResponse();
       loginResponse.setToken(jwtService.createToken(user));
       return loginResponse;

    }

//    @Override
//    public String updateUser(UUID id, UpdateRequest updateRequest){
//        User user = userRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("User does not exists"));
//
//
//    }

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
