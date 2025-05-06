package com.example.AulaTeste.model;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_animals")
public class AnimalModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "nm_animal")
    private String nome;

    @Column(name = "email_animal", unique = true)
    private String email;

    @Column(name = "senha_animal")
    private String senha;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public AnimalModel() {}

    public AnimalModel(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
