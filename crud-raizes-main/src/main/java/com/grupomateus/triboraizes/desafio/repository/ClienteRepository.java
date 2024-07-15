package com.grupomateus.triboraizes.desafio.repository;

import com.grupomateus.triboraizes.desafio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
