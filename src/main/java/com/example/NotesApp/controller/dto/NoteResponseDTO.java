package com.example.NotesApp.controller.dto;

import com.example.NotesApp.enumeration.ArchiveStatus;

public class NoteResponseDTO {
    private final Long id;
    private final String title;
    private final String body;
    private final ArchiveStatus archiveStatus;

    public NoteResponseDTO(Long id, String title, String body, ArchiveStatus archiveStatus) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.archiveStatus = archiveStatus;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public ArchiveStatus getArchiveStatus() {
        return archiveStatus;
    }
}
