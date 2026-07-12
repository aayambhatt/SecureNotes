package com.project.SecureNotes.service;

import com.project.SecureNotes.dto.CreateNoteRequest;
import com.project.SecureNotes.entity.Notes;
import com.project.SecureNotes.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<Notes> getAllNotes(UserDetails userDetails);

    Notes createNotes(CreateNoteRequest request, UserDetails userDetails);

    void deleteNoteById(UUID id, UserDetails userDetails);
}
