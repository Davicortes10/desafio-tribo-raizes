package com.grupomateus.triboraizes.desafio.dto;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        int quantidadeEmEstoque
) {
}
