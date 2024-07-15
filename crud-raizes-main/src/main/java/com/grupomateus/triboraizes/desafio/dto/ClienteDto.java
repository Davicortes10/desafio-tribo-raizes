package com.grupomateus.triboraizes.desafio.dto;

public record ClienteDto(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String endereco
) {
}
