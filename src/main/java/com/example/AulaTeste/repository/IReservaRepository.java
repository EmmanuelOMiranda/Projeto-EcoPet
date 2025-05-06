package com.example.AulaTeste.repository;

import com.example.AulaTeste.model.AnimalModel;
import com.example.AulaTeste.model.ReservaModel;
import com.example.AulaTeste.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IReservaRepository extends JpaRepository<ReservaModel, UUID> {
    List<ReservaModel> findByAnimal(AnimalModel animal);
    List<ReservaModel> findByUsuario(UserModel usuario);

    List<ReservaModel> findByAnimalAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqual(
            AnimalModel animal, LocalDate dataSaida, LocalDate dataEntrada);

    boolean existsByAnimalAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqual(
            AnimalModel animal, LocalDate dataSaida, LocalDate dataEntrada);

}
