package com.project.SecureNotes.service.impl;


import com.project.SecureNotes.dto.CreateNoteRequest;
import com.project.SecureNotes.entity.Notes;
import com.project.SecureNotes.entity.User;
import com.project.SecureNotes.repository.NoteRepository;
import com.project.SecureNotes.repository.UserRepository;
import com.project.SecureNotes.service.NoteService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notes createNotes(CreateNoteRequest request, UserDetails userDetails){
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Notes notes = new Notes();
        notes.setTitle(request.getTitle());
        notes.setContent(request.getContent());
        notes.setUser(user);

        return noteRepository.save(notes);
    }

    @Override
    public void deleteNoteById(UUID id, UserDetails userDetails){
            Notes fetchedNote = noteRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Notes not found"));

            if(!Objects.equals(fetchedNote.getUser().getEmail(), userDetails.getUsername())){
                throw new RuntimeException("You are not authorised to delete this note");
            }

            noteRepository.delete(fetchedNote);
    }

    @Override
    public List<Notes> getAllNotes(UserDetails userDetails){
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return noteRepository.findByUser(user);
    }
}
