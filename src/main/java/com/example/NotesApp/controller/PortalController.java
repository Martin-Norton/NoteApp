package com.example.NotesApp.controller;

import com.example.NotesApp.exception.MiException;
import com.example.NotesApp.model.Note;
import com.example.NotesApp.model.User;
import com.example.NotesApp.service.NoteService;
import com.example.NotesApp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    NoteService notaServicio;

    @Autowired
    UserService userService;

   /* @GetMapping("/")
    public String index(ModelMap modelo, HttpSession session) {
        User logueado = (User) session.getAttribute("usuariosession");

        List<Note> listaNotas = notaServicio.listarNotas();

        modelo.addAttribute("notas", listaNotas);

        return "index.html";
    }

    @GetMapping("/registrarUsuario")
    public String registrarUser() {
        return "registroUsuario.html";
    }
    @PostMapping("/registroUsuario")
    public String registroUsuario(String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo) {
        try {

            userService.registrar(nombre, email, password, password2);
            modelo.put("Exito", "usuario registrado correctamente");
            return "redirect:/index";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registroUsuario.html";
        }

    }
    @GetMapping("/login")
    public String login(@RequestParam(required=false)String error, ModelMap modelo){
        if (error != null) {
            modelo.put("error", "usuario o contraseña invalidos");
        }
        return "login.html";
    }

    @GetMapping("/inicio")
    public String inicio(HttpSession session) {

        User logueado = (User) session.getAttribute("usuariosession");

        return "inicio.html";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login.html";

    }
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session){

        User user = (User) session.getAttribute("usuariosession");
        modelo.put("usuario", user);

        return "usuario_modificar.html";
    }

    @PostMapping("/perfil/{id}")
    public String actualizar(@PathVariable String id, String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo){

        try {
            userService.actualizar(id, nombre, email, password, password2);

            modelo.put("exito", "usuario actualizado correctamente");

            return "index.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }
    }
    @GetMapping("/misNotas")
    public String misNotas(){
        return "misNotas.html";
    }*/
}
