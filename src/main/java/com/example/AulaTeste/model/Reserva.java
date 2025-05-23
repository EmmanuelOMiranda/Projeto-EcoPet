package com.example.AulaTeste.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_tutor")
    private String nomeTutor;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "nome_Pet")
    private String nomePet;

    @Column(name = "raca")
    private String raca;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "horario_reserva")
    private LocalTime horarioReserva;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public String getHorarioReservaFormatado() {
        return horarioReserva != null ? horarioReserva.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
    }
    public String getDataReservaFormatada() {
        if (dataReserva != null) {
            return dataReserva.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }
}