package com.example.AulaTeste.controller;

import com.example.AulaTeste.model.ProdutoModel;
import com.example.AulaTeste.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoModel produto) {
        var novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoModel produto) {
        try {
            ProdutoModel atualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable UUID id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado com sucesso.");
    }
}
