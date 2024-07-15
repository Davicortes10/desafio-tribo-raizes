package com.grupomateus.triboraizes.desafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 500, message = "A descrição do produto deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero")
    private BigDecimal preco;

    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a zero")
    private int quantidadeEmEstoque;

}
