package com.example.AulaTeste.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AulaTeste.errors.UsuarioJaExiste;
import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.repository.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.transaction.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private IUserRepository userRepository;

    public UserModel criarUsuario(UserModel userModel) {
        if (userRepository.findByEmail(userModel.getEmail()) != null) {
            throw new UsuarioJaExiste();
        }

        String senhaCriptografada = BCrypt.withDefaults()
                .hashToString(12, userModel.getSenha().toCharArray());

        userModel.setSenha(senhaCriptografada);
        return userRepository.save(userModel);
    }

    public List<UserModel> listarUsuarios() {

        return userRepository.findAll();
    }

    public UserModel buscarPorEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean autenticar(String email, String senha) {
        UserModel usuario = userRepository.findByEmail(email);
        if (usuario == null) return false;

        return BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha()).verified;
    }

    @Transactional
    public void deletarPorEmail(String email) {

        userRepository.deleteByEmail(email);
    }
}
