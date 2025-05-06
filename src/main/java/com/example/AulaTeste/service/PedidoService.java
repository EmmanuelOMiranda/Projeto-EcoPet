package com.example.AulaTeste.service;

import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.model.ProdutoModel;
import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.repository.IPedidoRepository;
import com.example.AulaTeste.repository.IProdutoRepository;
import com.example.AulaTeste.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    public PedidoModel criarPedido(UUID userId, List<UUID> produtosIds) {
        UserModel usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<ProdutoModel> produtos = produtoRepository.findAllById(produtosIds);

        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(usuario);
        pedido.setProdutos(produtos);

        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarPorUsuario(UUID userId) {
        UserModel usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pedidoRepository.findByUsuario(usuario);
    }

    public void deletarPedido(UUID pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
}
