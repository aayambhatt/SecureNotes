package com.project.SecureNotes.controller;


import com.project.SecureNotes.dto.CreateNoteRequest;
import com.project.SecureNotes.dto.NoteResponse;
import com.project.SecureNotes.entity.Notes;
import com.project.SecureNotes.mapper.NoteMapper;
import com.project.SecureNotes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;


    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }


    @PostMapping("/notes")
    public ResponseEntity<NoteResponse> createNote(
            @RequestBody CreateNoteRequest request,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        Notes savedNote = noteService.createNotes(request, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.toResponse(savedNote));
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNoteById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        noteService.deleteNoteById(id, userDetails);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/notes")
    public ResponseEntity<List<NoteResponse>> getAllNotes(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        List<Notes> notes = noteService.getAllNotes(userDetails);
        List<NoteResponse> response = notes.stream()
                .map(noteMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

}
