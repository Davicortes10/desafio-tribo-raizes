package com.grupomateus.triboraizes.desafio.model;

import com.grupomateus.triboraizes.desafio.dto.ClienteDto;
import com.grupomateus.triboraizes.desafio.mapper.ClienteMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O cliente é obrigatório")
    private Cliente cliente;

    @NotNull(message = "A data do pedido é obrigatória")
    private LocalDateTime dataPedido;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotEmpty(message = "O pedido deve ter pelo menos um item")
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @NotNull(message = "O valor total do pedido é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser maior que zero")
    private BigDecimal valorTotal;

    public Pedido(Long id, ClienteDto clienteDto, LocalDateTime dataPedido, List<ItemPedido> itensPedido, BigDecimal valorTotal) {
        this.id = id;
        this.cliente = ClienteMapper.toCliente(clienteDto);
        this.dataPedido = dataPedido;
        this.itensPedido = itensPedido;
        this.valorTotal = valorTotal;
    }
}
