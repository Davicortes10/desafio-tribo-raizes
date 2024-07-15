package com.grupomateus.triboraizes.desafio.controller;

import com.grupomateus.triboraizes.desafio.dto.ItemPedidoDto;
import com.grupomateus.triboraizes.desafio.dto.PedidoDto;
import com.grupomateus.triboraizes.desafio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:8080")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/realizarPedido")
    public ResponseEntity<PedidoDto> realizarPedido(@RequestParam Long clienteId, @RequestBody List<ItemPedidoDto> itensPedidos) {
        PedidoDto pedidoRealizado = service.realizarPedido(clienteId, itensPedidos);
        return new ResponseEntity<>(pedidoRealizado, HttpStatus.CREATED);
    }

    @GetMapping("/listarPedidos")
    public ResponseEntity<List<PedidoDto>> listarPedidos() {
        List<PedidoDto> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<PedidoDto> buscarPedidoPorId(@PathVariable Long id) {
        PedidoDto pedido = service.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        service.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        PedidoDto pedidoAtualizado = service.atualizarPedido(id, pedidoDto);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @GetMapping("/listarPedidosPorCliente/{clienteId}")
    public ResponseEntity<List<PedidoDto>> buscarPedidosPorCliente(@PathVariable Long clienteId) {
        List<PedidoDto> pedidos = service.buscarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }
}
