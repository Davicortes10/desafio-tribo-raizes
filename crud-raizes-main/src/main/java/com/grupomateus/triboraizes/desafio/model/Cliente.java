package com.grupomateus.triboraizes.desafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 2, max = 100, message = "O nome do cliente deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos")
    private String cpf;

    @NotBlank(message = "O email do cliente é obrigatório")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O telefone do cliente é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos")
    private String telefone;

    @NotBlank(message = "O endereço do cliente é obrigatório")
    private String endereco;
}
