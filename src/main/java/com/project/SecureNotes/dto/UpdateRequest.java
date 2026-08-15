package com.project.SecureNotes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRequest {

    private String name;
    private String currentPassword;
    private String password;
}
