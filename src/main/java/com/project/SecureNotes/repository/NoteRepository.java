package com.project.SecureNotes.repository;

import com.project.SecureNotes.entity.Notes;
import com.project.SecureNotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Notes, UUID> {

    List<Notes> findByUser(User user);

}
