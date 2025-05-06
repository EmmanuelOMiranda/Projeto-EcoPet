package com.example.AulaTeste.controller;

import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarPedido(@RequestParam UUID userId, @RequestBody List<UUID> produtosIds) {
        try {
            var pedido = pedidoService.criarPedido(userId, produtosIds);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/do-usuario")
    public ResponseEntity<?> listarPedidosPorUsuario(@RequestParam UUID userId) {
        try {
            List<PedidoModel> pedidos = pedidoService.listarPorUsuario(userId);
            return ResponseEntity.ok(pedidos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPedido(@PathVariable UUID id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok("Pedido deletado com sucesso.");
    }
}
