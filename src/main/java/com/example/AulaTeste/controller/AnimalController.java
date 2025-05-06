package com.example.AulaTeste.controller;

import java.util.List;
import java.util.UUID;

import com.example.AulaTeste.model.AnimalModel;
import com.example.AulaTeste.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarAnimal(@RequestBody AnimalModel animal, @RequestParam UUID userId) {
        try {
            var novoAnimal = animalService.cadastrarAnimal(animal, userId);
            return ResponseEntity.ok(novoAnimal);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @GetMapping("/do-usuario")
    public ResponseEntity<?> ListarPorUsuario(@RequestParam UUID userId) {
        try {
            var animais = animalService.listarPorUsuario(userId);
            return ResponseEntity.ok(animais);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editarAnimal(@PathVariable UUID id, @RequestBody AnimalModel novoAnimal){
        try {
            var animalAtualizado = animalService.editarAnimal(id, novoAnimal);
            return ResponseEntity.ok(animalAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAnimal(@PathVariable UUID id) {
        try {
            animalService.deletarAnimal(id);
            return ResponseEntity.ok().body("Animal deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

