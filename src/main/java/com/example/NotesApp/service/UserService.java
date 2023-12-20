package com.example.NotesApp.service;

import com.example.NotesApp.controller.dto.UserRequestDTO;
import com.example.NotesApp.controller.dto.UserResponseDTO;
import com.example.NotesApp.model.User;
import com.example.NotesApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(String userId) {
        return userRepository.findById(userId)
                .map(UserResponseDTO::new)
                .orElse(null);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User newUser = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(newUser);
        return new UserResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    public UserResponseDTO updateUser(String userId, UserRequestDTO userRequestDTO) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.updateFromDTO(userRequestDTO);
                    userRepository.save(existingUser);
                    return new UserResponseDTO(existingUser);
                })
                .orElse(null);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
