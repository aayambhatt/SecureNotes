package com.project.SecureNotes.service;


import com.project.SecureNotes.dto.RegisterRequest;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.exception.EmailAlreadyInUseException;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Nested
    @DisplayName("Register User Tests")
    class RegisterUserTests {

        @Test
        void registerUser_emailSuccess(){
            // Arrange
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .name("John")
                    .email("user@gmail.com")
                    .password("password")
                    .build();

            User mockedUser = new User();
            mockedUser.setName("John");
            mockedUser.setEmail("user@gmail.com");
            mockedUser.setPassword("password");



        }


        @Test
        void registerUser_emailAlreadyExists_throwsException() {
            // Arrange
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .name("John")
                    .email("user@gmail.com")
                    .password("password")
                    .build();
            when(userRepository.existsByEmail(registerRequest.getEmail()))
                    .thenReturn(true);

            assertThrows(EmailAlreadyInUseException.class,
                    ()->authService.registerUser(registerRequest));

        }



    }

}
