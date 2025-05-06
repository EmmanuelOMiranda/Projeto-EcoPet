package com.example.AulaTeste.service;
import com.example.AulaTeste.model.AnimalModel;
import com.example.AulaTeste.model.ReservaModel;
import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.repository.IAnimalRepository;
import com.example.AulaTeste.repository.IReservaRepository;
import com.example.AulaTeste.repository.IUserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private IAnimalRepository animalRepository;

    @Autowired
    private IUserRepository userRepository;


    @Transactional
    public ReservaModel criarReserva(UUID animalId, UUID userId, ReservaModel reserva) {
        AnimalModel animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado"));

        UserModel usuario = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        LocalDate entrada = reserva.getDataEntrada();
        LocalDate saida = reserva.getDataSaida();

        if (entrada == null || saida == null) {
            throw new IllegalArgumentException("Datas de entrada e saída devem ser informadas.");
        }

        if (entrada.isAfter(saida)) {
            throw new IllegalArgumentException("Data de entrada não pode ser depois da data de saída.");
        }

        boolean conflito = reservaRepository.existsByAnimalAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqual(
                animal, entrada, saida);

        if (conflito) {
            throw new IllegalStateException("Conflito de reserva: Período não disponível para este animal.");
        }

        reserva.setAnimal(animal);
        reserva.setUsuario(usuario);

        return reservaRepository.save(reserva);
    }

    public List<ReservaModel> listarPorAnimal(UUID animalId) {
        AnimalModel animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado"));

        return reservaRepository.findByAnimal(animal);
    }

    public List<ReservaModel> listarPorUsuario(UUID userId) {
        UserModel usuario = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return reservaRepository.findByUsuario(usuario);
    }

    public void deletarReserva(UUID reservaId) {
        if (!reservaRepository.existsById(reservaId)) {
            throw new IllegalArgumentException("Reserva não encontrada");
        }
        reservaRepository.deleteById(reservaId);
    }






}
