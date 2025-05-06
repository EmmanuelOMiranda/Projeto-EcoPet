package com.example.AulaTeste.repository;

import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IPedidoRepository extends JpaRepository<PedidoModel, UUID> {
    List<PedidoModel> findByUsuario(UserModel usuario);
}