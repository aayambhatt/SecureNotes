package com.project.SecureNotes.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NoteResponse {
    private UUID id;
    private String title;
    private String content;
}
