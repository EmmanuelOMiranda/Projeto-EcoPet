package com.example.AulaTeste.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AulaTeste.model.AnimalModel;
import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.repository.IAnimalRepository;
import com.example.AulaTeste.repository.IUserRepository;
@Service
public class AnimalService {

    @Autowired
    private IAnimalRepository animalRepository;

    @Autowired
    private IUserRepository userRepository;

    public AnimalModel cadastrarAnimal(AnimalModel animal, UUID userId) {
        UserModel tutor = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    public List<AnimalModel> listarPorUsuario(UUID userId) {
        UserModel tutor = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return animalRepository.findByTutor(tutor);
    }

    public void deletarAnimal(UUID id) {
        animalRepository.deleteById(id);
    }

    public AnimalModel editarAnimal(UUID id, AnimalModel novoAnimal) {
        return animalRepository.findById(id).map(animal -> {
            animal.setNome(novoAnimal.getNome());
            animal.setTipo(novoAnimal.getTipo());
            animal.setRaca(novoAnimal.getRaca());
            animal.setIdade(novoAnimal.getIdade());
            animal.setPorte(novoAnimal.getPorte());
            animal.setNecessidadeEspeciais(novoAnimal.getNecessidadeEspeciais());
            return animalRepository.save(animal);
        }).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }
}
