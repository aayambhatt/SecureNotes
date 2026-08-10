package com.project.SecureNotes.service.impl;

import com.project.SecureNotes.dto.LoginRequest;
import com.project.SecureNotes.dto.LoginResponse;
import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.dto.UpdateRequest;
import com.project.SecureNotes.entity.Role;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.exception.EmailAlreadyInUseException;
import com.project.SecureNotes.exception.InvalidCredentialsException;
import com.project.SecureNotes.exception.UnauthorizedActionException;
import com.project.SecureNotes.exception.UserNotFoundException;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.AuthService;
import com.project.SecureNotes.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            throw new EmailAlreadyInUseException("Email: " + request.getEmail() + " already exists");
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
                .orElseThrow(()-> new InvalidCredentialsException("Invalid Credentials"));

        // match password
       if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
           throw new InvalidCredentialsException("Invalid Credentials");
       }


       LoginResponse loginResponse = new LoginResponse();
       loginResponse.setToken(jwtService.createToken(user));
       return loginResponse;

    }

    @Override
    public String updateUser(UUID id, UpdateRequest updateRequest, UserDetails userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        if(!Objects.equals(user.getEmail(), userDetails.getUsername())){
            throw new UnauthorizedActionException("You are not authorized to update user details");
        }

        // Modify allowed fields
        if (updateRequest.getName() != null) {
            user.setName(updateRequest.getName());
        }

        userRepository.save(user);
        return "User updated successfully";
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // entity list, not strings

    }

    @Override
    public String deleteUserById(UUID id, UserDetails userDetails){

        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        if(!Objects.equals(user.getEmail(), userDetails.getUsername())){
            throw new RuntimeException("You are not authorised to delete this User");
        }

        String name = user.getName();
        userRepository.delete(user);

        return name;

    }
}
