package com.grupomateus.triboraizes.desafio.mapper;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.model.Cliente;

import java.util.List;

public class ClienteMapper {
    public static ClienteDto toClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );
    }

    public static Cliente toCliente(ClienteDto clienteDto) {
        return new Cliente(
                clienteDto.id(),
                clienteDto.nome(),
                clienteDto.cpf(),
                clienteDto.email(),
                clienteDto.telefone(),
                clienteDto.endereco()
        );
    }

    public static List<ClienteDto> toClienteDtoList(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::toClienteDto)
                .toList();
    }

    public static List<Cliente> toClienteList(List<ClienteDto> clientes) {
        return clientes.stream()
                .map(ClienteMapper::toCliente)
                .toList();
    }
}
