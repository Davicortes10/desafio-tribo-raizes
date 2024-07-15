package com.grupomateus.triboraizes.desafio.service;

import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.exceptions.ProdutoNaoEncontradoException;
import com.grupomateus.triboraizes.desafio.mapper.ProdutoMapper;
import com.grupomateus.triboraizes.desafio.model.Produto;
import com.grupomateus.triboraizes.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        Produto produto = ProdutoMapper.toProduto(produtoDto);
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoMapper.toProdutoDto(produtoSalvo);
    }

    public ProdutoDto buscarProdutoPorId(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado com o ID: " + id);
        }
        return ProdutoMapper.toProdutoDto(produtoOpt.get());
    }

    public void deletarProdutoPorId(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoDto atualizarProduto(ProdutoDto produtoDto) {
        if (!produtoRepository.existsById(produtoDto.id())) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado com o ID: " + produtoDto.id());
        }
        Produto produto = ProdutoMapper.toProduto(produtoDto);
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoMapper.toProdutoDto(produtoSalvo);
    }

    public List<ProdutoDto> listarTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoMapper.toProdutoDtoList(produtos);
    }
}
