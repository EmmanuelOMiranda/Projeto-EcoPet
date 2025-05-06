package com.example.AulaTeste.errors;

public class UsuarioJaExiste extends RuntimeException {
    public UsuarioJaExiste() {
        super("Usuário já existe");
    }
}
