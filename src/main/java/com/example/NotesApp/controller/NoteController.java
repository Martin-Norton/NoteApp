package com.example.NotesApp.controller;

import com.example.NotesApp.controller.dto.NoteRequestDTO;
import com.example.NotesApp.controller.dto.NoteResponseDTO;
import com.example.NotesApp.service.NoteService;
import com.example.NotesApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public List<NoteResponseDTO> getAll(@PathVariable("userId") String userId) {
        return noteService.getByAllUserId(userId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponseDTO create(@PathVariable("userId") String userId,
                                  @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        return noteService.create(userId, noteRequestDTO);
    }
    @PostMapping("/{userId}/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponseDTO update(@PathVariable("userId") String userId,
                                  @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        Long noteId = noteRequestDTO.getId();
        return noteService.update(userId, noteId, noteRequestDTO);
    }
    @DeleteMapping("/{userId}/notes/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") String userId,
                           @PathVariable("noteId") Long noteId) {
        noteService.delete(noteId);
    }
    @GetMapping("/{userId}/unarchived")
    public List<NoteResponseDTO> getUnArchived(@PathVariable("userId") String userId) {
        return noteService.getNotArchivedNotesByUserId(userId);
    }
    @GetMapping("/{userId}/archived")
    public List<NoteResponseDTO> getArchived(@PathVariable("userId") String userId) {
        return noteService.getArchivedNotesByUserId(userId);
    }
    @PutMapping("/{userId}/notes/{noteId}/archive")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponseDTO toggleNoteArchiveStatus(@PathVariable("userId") String userId,
                                                   @PathVariable("noteId") Long noteId) {
        return noteService.toggleNoteArchiveStatus(userId, noteId);
    }

    /* @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("nota", noteService.getOne(id));

        return "nota_modificar.html";
    }

    @GetMapping("/archivar/{id}")
    public String archivar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("nota", noteService.getOne(id));

        return "nota_archivar.html";
    }

    @GetMapping("/desarchivar/{id}")
    public String desarchivar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("nota", noteService.getOne(id));

        return "nota_desarchivar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, @RequestParam String titulo, String cuerpo, ModelMap modelo) {
        try {
            noteService.modificarNota(id, titulo, cuerpo);

            return "redirect:../";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "nota_modificar.html";
        }
    }

    @PostMapping("/archivar/{id}")
    public String archivar(@PathVariable Long id, ModelMap modelo, boolean arch) {
        try {
            noteService.archivarNota(id);

            return "redirect:../";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "nota_archivar.html";
        }
    }

    @PostMapping("/desarchivar/{id}")
    public String desarchivar(@PathVariable Long id, ModelMap modelo, boolean arch) {
        try {
            noteService.desarchivarNota(id);

            return "redirect:../";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "nota_desarchivar.html";
        }
    }

    @GetMapping("/misNotas/{id}")
    public String misNotas(@RequestParam String id, ModelMap modelo) {

        List<Note> misNotas = noteService.findByUser(id);

        modelo.addAttribute("misNotas", misNotas);

        return "misNotas.html";
    }

    @GetMapping("/misNotasArchivadas/")
    public String misNotasArchivadas(ModelMap modelo, String idUsuario) {

        List<Note> misNotasArchivadas = noteRepo.buscarPorArchivadas(idUsuario);

        modelo.addAttribute("misNotasArchivadas", misNotasArchivadas);

        return "misNotasArchivadas.html";
    }

    @GetMapping("/misNotasDesarchivadas/")
    public String misNotasDesarchivadas(ModelMap modelo, String idUsuario) {

        List<Note> misNotasDesarchivadas = noteRepo.buscarPorArchivadas(idUsuario);

        modelo.addAttribute("misNotasDesarchivadas", misNotasDesarchivadas);

        return "misNotasDesarchivadas.html";
    }

    @GetMapping("/vista/{id}")
    public String verNota(@PathVariable("id") Long id, ModelMap model) {
        Note nota = noteService.getOne(id);
        if (nota == null) {
            return "nota-no-encontrada";
        }
        model.addAttribute("nota", nota);
        return "nota_vista.html";
    }*/

}

