package com.example.AulaTeste.repository;

import com.example.AulaTeste.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
}
