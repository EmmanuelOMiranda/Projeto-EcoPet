package com.example.AulaTeste.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_produtos")
public class ProdutoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    private String descricao;

    private double preco;

    private int estoque;
}