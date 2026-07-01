package com.project.SecureNotes.dto;


import com.project.SecureNotes.entity.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String email;
    private String name;
    private Role role;
}
