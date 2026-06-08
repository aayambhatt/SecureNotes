package com.project.SecureNotes.controller;


import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.service.AuthService;
import com.project.SecureNotes.service.impl.AuthServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public void register(@RequestBody RegisterRequest request){
        authService.registerUser(request);

    }

    @GetMapping("/auth/getUsers")
    public String getUsers(){
      return  authService.getAllUsers();
    }


}
