package com.example.NotesApp.service;

import com.example.NotesApp.controller.dto.NoteRequestDTO;
import com.example.NotesApp.controller.dto.NoteResponseDTO;
import com.example.NotesApp.enumeration.ArchiveStatus;
import com.example.NotesApp.exception.NotFoundException;
import com.example.NotesApp.exception.NoteNotFoundException;
import com.example.NotesApp.model.Note;
import com.example.NotesApp.model.User;
import com.example.NotesApp.repository.NoteRepository;
import com.example.NotesApp.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

   /* @Transactional
    public void crearNota(String titulo, String cuerpo, String idUsuario) throws MiException {
        UserService us;

        Optional<User> respuesta = userRepository.findById(idUsuario);

        User user = null;

        if (respuesta.isPresent()) {
            user = respuesta.get();
        }

        validar(titulo, cuerpo);

        Note nota = new Note(titulo, cuerpo, false, idUsuario);


        noteRepository.save(nota);
    }

    public List<Note> listarNotas() {
        List<Note> notas = new ArrayList();

        notas = noteRepository.findAll();

        return notas;
    }

    public List<Note> listarNotasArchivadas(String idUsuario) {
        List<Note> notasArchivadas = new ArrayList();

        notasArchivadas = noteRepository.buscarPorArchivadas(idUsuario);

        return notasArchivadas;
    }

    public List<Note> listarNotasNoArchivadas(String idUsuario) {
        List<Note> notasNoArchivadas = new ArrayList();

        notasNoArchivadas = noteRepository.buscarPorNoArchivadas(idUsuario);

        return notasNoArchivadas;
    }

    @Transactional
    public void modificarNota(Long id, String titulo, String cuerpo) throws MiException {

        validar(titulo, cuerpo);

        Optional<Note> respuesta = noteRepository.findById(id);

        if (respuesta.isPresent()) {

            Note nota = respuesta.get();

            nota.setTitle(titulo);
            nota.setBody(cuerpo);

            noteRepository.save(nota);
        }
    }

    @Transactional
    public void archivarNota(Long id) throws MiException {

        Optional<Note> respuesta = noteRepository.findById(id);

        if (respuesta.isPresent()) {

            Note nota = respuesta.get();

            nota.setArchived(true);

            noteRepository.save(nota);
        }
    }

    @Transactional
    public void desarchivarNota(Long id) throws MiException {

        Optional<Note> respuesta = noteRepository.findById(id);

        if (respuesta.isPresent()) {

            Note nota = respuesta.get();

            nota.setArchived(false);

            noteRepository.save(nota);
        }
    }

    public Note getOne(Long id) {
        return noteRepository.getOne(id);
    }

    private void validar(String titulo, String cuerpo) throws MiException {

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede ser nulo o estar vacio");
        }
    }

    public void validarTitulo(String titulo) throws MiException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
    }*/

    public List<Note> findByUser(String id) {
        return noteRepository.findByUserId(id);
    }

    public NoteResponseDTO create(String userId, NoteRequestDTO noteRequestDTO) {
        User user = getUser(userId);
        Note entity = new Note(noteRequestDTO.getTitle(), noteRequestDTO.getBody(), ArchiveStatus.NOT_ARCHIVED, user);
        Note saved = noteRepository.save(entity);

        return new NoteResponseDTO(saved.getId(), saved.getTitle(), saved.getBody(), saved.getArchiveStatus());
    }

    public List<NoteResponseDTO> getByAllUserId(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("User %s not found.", userId));
        }

        return noteRepository.findByUserId(userId).stream().map(note -> new NoteResponseDTO(note.getId(), note.getTitle(), note.getBody(), note.getArchiveStatus())).collect(Collectors.toList());
    }
    public List<NoteResponseDTO> getNotArchivedNotesByUserId(String userId) {
        List<Note> notes = noteRepository.findByUserIdAndArchiveStatus(userId, ArchiveStatus.NOT_ARCHIVED);
        return convertToNoteResponseDTOList(notes);
    }
    public List<NoteResponseDTO> getArchivedNotesByUserId(String userId) {
        List<Note> archivedNotes = noteRepository.findByUserIdAndArchiveStatus(userId, ArchiveStatus.ARCHIVED);
        return convertToNoteResponseDTOList(archivedNotes);
    }

    @NotNull
    private User getUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(String.format("User %s not found.", userId));
        }
        return optionalUser.get();
    }

    public NoteResponseDTO update(String userId, Long noteId, NoteRequestDTO noteRequestDTO) {
        User user = getUser(userId);
        Optional<Note> note1 = noteRepository.findById(noteId);

        Note existingNote =  note1.orElseThrow(() -> new NoteNotFoundException(noteId));
        Note updatedNote = new Note(existingNote.getId(), noteRequestDTO.getTitle(), noteRequestDTO.getBody(), ArchiveStatus.NOT_ARCHIVED, user);
        Note saved = noteRepository.save(updatedNote);

        return new NoteResponseDTO(saved.getId(), saved.getTitle(), saved.getBody(), saved.getArchiveStatus());
    }

    public void delete(Long noteId) {

        Optional<Note> note1 = noteRepository.findById(noteId);

        Note existingNote =  note1.orElseThrow(() -> new NoteNotFoundException(noteId));

        noteRepository.delete(existingNote);
    }
    private List<NoteResponseDTO> convertToNoteResponseDTOList(List<Note> notes) {
        List<NoteResponseDTO> noteResponseDTOList = new ArrayList<>();

        for (Note note : notes) {
            NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                    note.getId(),
                    note.getTitle(),
                    note.getBody(),
                    note.getArchiveStatus()
            );
            noteResponseDTOList.add(noteResponseDTO);
        }

        return noteResponseDTOList;
    }
    public NoteResponseDTO toggleNoteArchiveStatus(String userId, Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);

        Note existingNote = noteOptional.orElseThrow(() -> new NoteNotFoundException(noteId));

        existingNote.toggleArchiveStatus();

        Note saved = noteRepository.save(existingNote);
        return new NoteResponseDTO(saved.getId(), saved.getTitle(), saved.getBody(), saved.getArchiveStatus());
    }

}

