package com.example.AulaTeste.controller;

import com.example.AulaTeste.model.ReservaModel;
import com.example.AulaTeste.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarReserva(
            @RequestParam UUID animalId,
            @RequestParam UUID userId,
                @RequestBody ReservaModel reserva) {
        try {
            var novaReserva = reservaService.criarReserva(animalId, userId, reserva);
            return ResponseEntity.ok(novaReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/do-animal")
    public ResponseEntity<?> listarPorAnimal(@RequestParam UUID animalId) {
        try {
            List<ReservaModel> reservas = reservaService.listarPorAnimal(animalId);
            return ResponseEntity.ok(reservas);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/do-usuario")
    public ResponseEntity<?> listarPorUsuario(@RequestParam UUID userId) {
        try {
            List<ReservaModel> reservas = reservaService.listarPorUsuario(userId);
            return ResponseEntity.ok(reservas);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.ok("Reserva deletada com sucesso.");
    }
}
