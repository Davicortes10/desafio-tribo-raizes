package com.grupomateus.triboraizes.desafio.service;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.dto.ItemPedidoDto;
import com.grupomateus.triboraizes.desafio.dto.PedidoDto;
import com.grupomateus.triboraizes.desafio.dto.ProdutoDto;
import com.grupomateus.triboraizes.desafio.exceptions.ClienteNaoEncontradoException;
import com.grupomateus.triboraizes.desafio.exceptions.EstoqueInsuficienteException;
import com.grupomateus.triboraizes.desafio.exceptions.ProdutoNaoEncontradoException;
import com.grupomateus.triboraizes.desafio.mapper.ClienteMapper;
import com.grupomateus.triboraizes.desafio.mapper.PedidoMapper;
import com.grupomateus.triboraizes.desafio.mapper.ProdutoMapper;
import com.grupomateus.triboraizes.desafio.model.ItemPedido;
import com.grupomateus.triboraizes.desafio.model.Pedido;
import com.grupomateus.triboraizes.desafio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public PedidoDto realizarPedido(Long clienteId, List<ItemPedidoDto> itensPedidos) {
        ClienteDto clienteDto = validarCliente(clienteId);
        List<ItemPedido> itemPedidoList = new ArrayList<>();
        BigDecimal valorTotal = calcularValorTotalEAtualizarEstoque(itensPedidos, itemPedidoList);

        Pedido pedido = new Pedido(null, ClienteMapper.toCliente(clienteDto), LocalDateTime.now(), itemPedidoList, valorTotal);
        pedidoRepository.save(pedido);

        return PedidoMapper.toPedidoDto(pedido);
    }

    private BigDecimal calcularValorTotalEAtualizarEstoque(List<ItemPedidoDto> itensPedidos, List<ItemPedido> itemPedidoList) {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoDto itemPedidoDto : itensPedidos) {
            ProdutoDto produtoDto = produtoService.buscarProdutoPorId(itemPedidoDto.produtoId());
            validarProduto(produtoDto, itemPedidoDto.quantidade());

            atualizarEstoqueProduto(produtoDto, itemPedidoDto.quantidade());

            ItemPedido itemPedido = criarItemPedido(itemPedidoDto, produtoDto);
            itemPedidoList.add(itemPedido);

            valorTotal = valorTotal.add(produtoDto.preco().multiply(BigDecimal.valueOf(itemPedidoDto.quantidade())));
        }

        return valorTotal;
    }

    private void validarProduto(ProdutoDto produtoDto, int quantidade) {
        if (produtoDto == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        if (produtoDto.quantidadeEmEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Quantidade em estoque insuficiente");
        }
    }

    private void atualizarEstoqueProduto(ProdutoDto produtoDto, int quantidade) {
        ProdutoDto novoProduto = new ProdutoDto(
                produtoDto.id(),
                produtoDto.nome(),
                produtoDto.descricao(),
                produtoDto.preco(),
                produtoDto.quantidadeEmEstoque() - quantidade
        );
        produtoService.atualizarProduto(novoProduto);
    }

    private ItemPedido criarItemPedido(ItemPedidoDto itemPedidoDto, ProdutoDto produtoDto) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(ProdutoMapper.toProduto(produtoDto));
        itemPedido.setQuantidade(itemPedidoDto.quantidade());
        return itemPedido;
    }

    private ClienteDto validarCliente(Long clienteId) {
        ClienteDto clienteDto = clienteService.buscarClientePorId(clienteId);
        if (clienteDto == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return clienteDto;
    }

    public List<PedidoDto> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return PedidoMapper.toPedidoDtoList(pedidos);
    }

    public PedidoDto buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Pedido não encontrado com o ID: " + id));
        return PedidoMapper.toPedidoDto(pedido);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Pedido não encontrado com o ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    public PedidoDto atualizarPedido(Long id, PedidoDto pedidoDto) {
        if (!pedidoRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Pedido não encontrado com o ID: " + id);
        }
        Pedido pedido = PedidoMapper.toPedido(pedidoDto);
        pedido.setId(id);
        pedidoRepository.save(pedido);
        return PedidoMapper.toPedidoDto(pedido);
    }

    public List<PedidoDto> buscarPedidosPorCliente(Long clienteId) {
        ClienteDto clienteDto = validarCliente(clienteId);
        List<Pedido> pedidos = pedidoRepository.findByCliente(ClienteMapper.toCliente(clienteDto));
        return PedidoMapper.toPedidoDtoList(pedidos);
    }
}
