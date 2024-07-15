package com.grupomateus.triboraizes.desafio.repository;

import com.grupomateus.triboraizes.desafio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
