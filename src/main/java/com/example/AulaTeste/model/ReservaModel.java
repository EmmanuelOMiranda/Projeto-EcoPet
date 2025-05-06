package com.example.AulaTeste.model;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "tb_reservas")
public class ReservaModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "data_saida", nullable = false)
    private LocalDate dataSaida;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id")
    private AnimalModel animal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
