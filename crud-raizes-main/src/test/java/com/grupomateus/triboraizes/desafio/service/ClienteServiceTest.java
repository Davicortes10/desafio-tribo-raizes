package com.grupomateus.triboraizes.desafio.service;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.mapper.ClienteMapper;
import com.grupomateus.triboraizes.desafio.model.Cliente;
import com.grupomateus.triboraizes.desafio.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve cadastrar um cliente")
    public void deveCadastrarCliente() {
        ClienteDto clienteDto = cadastrarCliente();
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        Cliente clienteSalvo = ClienteMapper.toCliente(clienteDto);
        clienteSalvo.setId(1L);
        when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);

        ClienteDto clienteSalvoDto = clienteService.cadastrarCliente(clienteDto);

        assertEquals(clienteSalvoDto, clienteDto);
    }

    @Test
    @DisplayName("Deve buscar um cliente por id")
    public void deveBuscarClientePorId() {
        ClienteDto clienteDto = cadastrarCliente();
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));

        ClienteDto clienteSalvoDto = clienteService.buscarClientePorId(1L);

        assertEquals(clienteSalvoDto, clienteDto);
    }

    @Test
    @DisplayName("Deve deletar um cliente por id")
    public void deveDeletarClientePorId() {
        ClienteDto clienteDto = cadastrarCliente();
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setId(1L);
        when(clienteRepository.existsById(1L)).thenReturn(true);

        clienteService.deletarClientePorId(1L);
    }

    @Test
    @DisplayName("Deve atualizar um cliente")
    public void deveAtualizarCliente() {
        ClienteDto clienteDto = cadastrarCliente();
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setId(1L);
        when(clienteRepository.existsById(1L)).thenReturn(true);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteDto clienteSalvoDto = clienteService.atualizarCliente(clienteDto);

        assertEquals(clienteSalvoDto, clienteDto);
    }

    @Test
    @DisplayName("Deve listar todos os clientes")
    public void deveListarTodosClientes() {
        ClienteDto clienteDto = cadastrarCliente();
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setId(1L);
        when(clienteRepository.findAll()).thenReturn(java.util.List.of(cliente));

        var clientes = clienteService.listarTodosClientes();

        assertEquals(clientes.size(), 1);
        assertEquals(clientes.get(0), clienteDto);
    }

    private ClienteDto cadastrarCliente() {
        return new ClienteDto(1L, "João", "12345678901", "joao@gmail.com", "646565", "rua um");
    }
}