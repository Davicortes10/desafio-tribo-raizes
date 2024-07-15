package com.grupomateus.triboraizes.desafio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        Long id,
        Long clienteId,
        LocalDateTime dataPedido,
        List<ItemPedidoDto> itensPedido,
        BigDecimal valorTotal
) {
}
