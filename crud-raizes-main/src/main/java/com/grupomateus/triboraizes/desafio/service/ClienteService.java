package com.grupomateus.triboraizes.desafio.service;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.exceptions.ClienteNaoEncontradoException;
import com.grupomateus.triboraizes.desafio.mapper.ClienteMapper;
import com.grupomateus.triboraizes.desafio.model.Cliente;
import com.grupomateus.triboraizes.desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDto cadastrarCliente(ClienteDto clienteDto) {
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDto(clienteSalvo);
    }

    public ClienteDto buscarClientePorId(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        return ClienteMapper.toClienteDto(clienteOpt.get());
    }

    public void deletarClientePorId(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public ClienteDto atualizarCliente(ClienteDto clienteDto) {
        if (!clienteRepository.existsById(clienteDto.id())) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + clienteDto.id());
        }
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDto(clienteSalvo);
    }

    public List<ClienteDto> listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteMapper.toClienteDtoList(clientes);
    }
}
