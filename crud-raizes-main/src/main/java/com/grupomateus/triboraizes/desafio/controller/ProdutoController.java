package com.grupomateus.triboraizes.desafio.controller;

import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque/produtos")
@CrossOrigin(origins = "http://localhost:8080")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDto> cadastrarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        ProdutoDto produtoCadastrado = produtoService.cadastrarProduto(produtoDto);
        return new ResponseEntity<>(produtoCadastrado, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ProdutoDto> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoDto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ProdutoDto> atualizarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        ProdutoDto produtoAtualizado = produtoService.atualizarProduto(produtoDto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ProdutoDto>> listarTodosProdutos() {
        List<ProdutoDto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }
}
