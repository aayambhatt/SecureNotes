package com.project.SecureNotes.dto;


import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @Email
    private String email;

    private String name;

    private String password;
}
