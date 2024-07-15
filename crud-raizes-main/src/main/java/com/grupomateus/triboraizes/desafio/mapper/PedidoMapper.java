package com.grupomateus.triboraizes.desafio.mapper;

import com.grupomateus.triboraizes.desafio.dto.PedidoDto;
import com.grupomateus.triboraizes.desafio.model.Pedido;
import com.grupomateus.triboraizes.desafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PedidoMapper {
    @Autowired
    private static ClienteService clienteService;

    public static PedidoDto toPedidoDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getDataPedido(),
                ItemPedidoMapper.toItemPedidoDtoList(pedido.getItensPedido()),
                pedido.getValorTotal()
        );
    }

    public static Pedido toPedido(PedidoDto pedidoDto) {
        return new Pedido(
                pedidoDto.id(),
                clienteService.buscarClientePorId(pedidoDto.clienteId()),
                pedidoDto.dataPedido(),
                ItemPedidoMapper.toItemPedidoList(pedidoDto.itensPedido()),
                pedidoDto.valorTotal()
        );
    }

    public static List<PedidoDto> toPedidoDtoList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoMapper::toPedidoDto)
                .toList();
    }



    public static List<Pedido> toPedidoList(List<PedidoDto> pedidoDtos) {
        return pedidoDtos.stream()
                .map(PedidoMapper::toPedido)
                .toList();
    }
}
