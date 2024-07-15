package com.grupomateus.triboraizes.desafio.dto;

public record ItemPedidoDto(
        Long id,
        Long produtoId,
        int quantidade
) {
}
