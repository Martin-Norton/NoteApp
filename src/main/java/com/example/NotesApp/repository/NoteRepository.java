package com.example.NotesApp.repository;

import com.example.NotesApp.enumeration.ArchiveStatus;
import com.example.NotesApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(String userId);

    Optional<Note> findById(Long id);

    @Override
    void deleteById(Long id);
    List<Note> findByUserIdAndArchiveStatus(String userId, ArchiveStatus archiveStatus);
}

