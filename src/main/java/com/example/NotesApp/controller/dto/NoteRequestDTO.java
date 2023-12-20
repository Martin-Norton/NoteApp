package com.example.NotesApp.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class NoteRequestDTO {
    private Long id;
    @NotBlank
    private final String title;
    @NotBlank
    private final String body;

    public NoteRequestDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NoteRequestDTO(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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
}


