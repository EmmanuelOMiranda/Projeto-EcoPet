package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.AulaTeste.errors.UsuarioJaExiste;
import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.service.UsuarioService;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody UserModel userModel) {
        try {
            var user = usuarioService.criarUsuario(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UsuarioJaExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        var users = usuarioService.listarUsuarios();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserModel> getUser(@PathVariable String email) {
        var user = usuarioService.buscarPorEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel userModel) {
        boolean autenticado = usuarioService.autenticar(userModel.getEmail(), userModel.getSenha());
        if (autenticado) {
            return ResponseEntity.ok("Autenticação bem-sucedida");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorreta");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletUser(@PathVariable String email) {
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
