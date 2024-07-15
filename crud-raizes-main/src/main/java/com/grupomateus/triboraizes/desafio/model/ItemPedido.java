package com.grupomateus.triboraizes.desafio.model;

import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.mapper.ProdutoMapper;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    public ItemPedido(Long id, ProdutoDto produtoDto, int quantidade) {
        this.id = id;
        this.produto = ProdutoMapper.toProduto(produtoDto);
        this.quantidade = quantidade;
    }
}
