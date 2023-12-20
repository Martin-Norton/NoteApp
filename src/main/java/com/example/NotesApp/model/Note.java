package com.example.NotesApp.model;

import com.example.NotesApp.enumeration.ArchiveStatus;
import jakarta.persistence.*;



@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private ArchiveStatus archiveStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {
    }

    public Note(Long id, String title, String body, ArchiveStatus archiveStatus, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.archiveStatus = archiveStatus;
        this.user = user;
    }

    public Note(String title, String body, ArchiveStatus archiveStatus, User user) {
        this.title = title;
        this.body = body;
        this.archiveStatus = archiveStatus;
        this.user = user;
    }

    public void toggleArchiveStatus() {
        this.archiveStatus = (this.archiveStatus == ArchiveStatus.ARCHIVED) ?
                ArchiveStatus.NOT_ARCHIVED : ArchiveStatus.ARCHIVED;
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

    public User getUser() {
        return user;
    }
}
