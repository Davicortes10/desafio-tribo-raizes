package com.grupomateus.triboraizes.desafio.mapper;

import com.grupomateus.triboraizes.desafio.dto.ItemPedidoDto;
import com.grupomateus.triboraizes.desafio.model.ItemPedido;
import com.grupomateus.triboraizes.desafio.service.PedidoService;
import com.grupomateus.triboraizes.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemPedidoMapper {
    @Autowired
    private static PedidoService pedidoService;
    @Autowired
    private static ProdutoService produtoService;

    public static ItemPedidoDto toItemPedidoDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(
                itemPedido.getId(),
                itemPedido.getProduto().getId(),
                itemPedido.getQuantidade()
        );
    }

    public static ItemPedido toItemPedido(ItemPedidoDto itemPedidoDto) {
        return new ItemPedido(
                itemPedidoDto.id(),
                produtoService.buscarProdutoPorId(itemPedidoDto.produtoId()),
                itemPedidoDto.quantidade()
        );
    }

    public static List<ItemPedidoDto> toItemPedidoDtoList(List<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .map(ItemPedidoMapper::toItemPedidoDto)
                .toList();
    }

    public static List<ItemPedido> toItemPedidoList(List<ItemPedidoDto> itemPedidoDtos) {
        return itemPedidoDtos.stream()
                .map(ItemPedidoMapper::toItemPedido)
                .toList();
    }
}
