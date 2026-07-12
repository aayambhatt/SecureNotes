package com.project.SecureNotes.mapper;

import com.project.SecureNotes.dto.NoteResponse;
import com.project.SecureNotes.entity.Notes;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteResponse toResponse(Notes notes){
        NoteResponse noteResponse = new NoteResponse();
        noteResponse.setId(notes.getId());
        noteResponse.setTitle(notes.getTitle());
        noteResponse.setContent(notes.getContent());
        return noteResponse;
    }

}
