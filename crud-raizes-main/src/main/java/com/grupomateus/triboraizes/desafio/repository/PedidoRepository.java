package com.grupomateus.triboraizes.desafio.repository;

import com.grupomateus.triboraizes.desafio.model.Cliente;
import com.grupomateus.triboraizes.desafio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente(Cliente cliente);
}
