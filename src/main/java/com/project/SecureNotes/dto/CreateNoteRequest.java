package com.project.SecureNotes.dto;

import lombok.Data;

@Data
public class CreateNoteRequest {

    private String title;
    private String content;
}
