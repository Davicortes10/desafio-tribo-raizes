package com.grupomateus.triboraizes.desafio.controller;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastrarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteCadastrado = service.cadastrarCliente(clienteDto);
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
        ClienteDto cliente = service.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable Long id) {
        service.deletarClientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteDto> atualizarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteAtualizado = service.atualizarCliente(clienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ClienteDto>> listarTodosClientes() {
        List<ClienteDto> clientes = service.listarTodosClientes();
        return ResponseEntity.ok(clientes);
    }
}
