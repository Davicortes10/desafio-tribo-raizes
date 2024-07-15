package com.grupomateus.triboraizes.desafio.mapper;

import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.model.Produto;

import java.util.List;

public class ProdutoMapper {
    public static ProdutoDto toProdutoDto(Produto produto) {
        return new ProdutoDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEmEstoque()
        );
    }

    public static Produto toProduto(ProdutoDto produtoDto) {
        return new Produto(
                produtoDto.id(),
                produtoDto.nome(),
                produtoDto.descricao(),
                produtoDto.preco(),
                produtoDto.quantidadeEmEstoque()
        );
    }

    public static List<ProdutoDto> toProdutoDtoList(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::toProdutoDto)
                .toList();
    }

    public static List<Produto> toProdutoList(List<ProdutoDto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::toProduto)
                .toList();
    }
}
