package com.example.NotesApp.model;

import com.example.NotesApp.controller.dto.UserRequestDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;


    public User() {
    }

    public User(String id, String name, String email, String password, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void updateFromDTO(UserRequestDTO userRequestDTO) {
    }
}
