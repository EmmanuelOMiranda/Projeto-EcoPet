package com.example.AulaTeste.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_pedidos")
public class PedidoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    @ManyToMany
    @JoinTable(
            name = "tb_pedido_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoModel> produtos;

    @CreationTimestamp
    private LocalDateTime dataCriacao;
}
