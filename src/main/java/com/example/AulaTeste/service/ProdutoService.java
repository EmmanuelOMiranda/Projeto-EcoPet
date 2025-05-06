package com.example.AulaTeste.service;

import com.example.AulaTeste.model.ProdutoModel;
import com.example.AulaTeste.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    public ProdutoModel criarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel atualizarProduto(UUID id, ProdutoModel dadosAtualizados) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dadosAtualizados.getNome());
        produto.setDescricao(dadosAtualizados.getDescricao());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setEstoque(dadosAtualizados.getEstoque());

        return produtoRepository.save(produto);
    }

    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }
}
