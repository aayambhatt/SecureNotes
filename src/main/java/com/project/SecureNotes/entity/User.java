package com.project.SecureNotes.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   @Column(columnDefinition = "uuid", updatable = false, nullable = false)
   private UUID id;
   private String email;
   private String password;
   @Enumerated(EnumType.STRING)
   private Role role;


}
