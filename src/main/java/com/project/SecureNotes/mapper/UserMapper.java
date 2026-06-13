package com.project.SecureNotes.mapper;


import com.project.SecureNotes.dto.UserResponse;
import com.project.SecureNotes.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        return userResponse;

    }
}
