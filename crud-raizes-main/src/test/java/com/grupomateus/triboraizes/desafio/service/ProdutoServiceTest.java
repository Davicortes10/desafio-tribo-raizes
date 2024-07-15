package com.grupomateus.triboraizes.desafio.service;

import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.mapper.ProdutoMapper;
import com.grupomateus.triboraizes.desafio.model.Produto;
import com.grupomateus.triboraizes.desafio.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;


    @Test
    @DisplayName("Deve cadastrar um produto")
    public void deveCadastrarProduto() {
         ProdutoDto produtoDto = cadastrarProduto();
         Produto produto = ProdutoMapper.toProduto(produtoDto);
         Produto produtoSalvo = ProdutoMapper.toProduto(produtoDto);
         produtoSalvo.setId(1L);
         when(produtoRepository.save(produto)).thenReturn(produtoSalvo);

         ProdutoDto produtoSalvoDto = produtoService.cadastrarProduto(produtoDto);

         assertEquals(produtoSalvoDto, produtoDto);
    }

    @Test
    @DisplayName("Deve buscar um produto por id")
    public void deveBuscarProdutoPorId() {
        ProdutoDto produtoDto = cadastrarProduto();
        Produto produto = ProdutoMapper.toProduto(produtoDto);
        produto.setId(1L);
        when(produtoRepository.findById(1L)).thenReturn(java.util.Optional.of(produto));

        ProdutoDto produtoSalvoDto = produtoService.buscarProdutoPorId(1L);

        assertEquals(produtoSalvoDto, produtoDto);
    }

    @Test
    @DisplayName("Deve deletar um produto por id")
    public void deveDeletarProdutoPorId() {
        ProdutoDto produtoDto = cadastrarProduto();
        Produto produto = ProdutoMapper.toProduto(produtoDto);
        produto.setId(1L);
        when(produtoRepository.existsById(1L)).thenReturn(true);

        produtoService.deletarProdutoPorId(1L);
    }

    private ProdutoDto cadastrarProduto() {
        return new ProdutoDto(1L, "Maca", "muito deliciosa", BigDecimal.valueOf(2.0), 10);
    }
}