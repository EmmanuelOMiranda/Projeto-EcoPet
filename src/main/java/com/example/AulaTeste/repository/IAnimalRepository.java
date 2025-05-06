package com.example.AulaTeste.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AulaTeste.model.AnimalModel;
import com.example.AulaTeste.model.UserModel;

public interface IAnimalRepository extends JpaRepository<AnimalModel, UUID> {
    AnimalModel findBynome(String nome);
    List<AnimalModel> findByTutor(UserModel tutor);
    List<AnimalModel> findByTipo(String tipo);
    }


