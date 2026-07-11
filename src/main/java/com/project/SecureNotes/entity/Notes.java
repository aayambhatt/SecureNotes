package com.project.SecureNotes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "notes")
@Data
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // a note has exactly one user


}
