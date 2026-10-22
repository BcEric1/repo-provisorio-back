package com.kawz.tattoo.domain.repository;

import com.kawz.tattoo.domain.model.MovimentacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Integer> {
    List<MovimentacaoFinanceira> findByProjetoIdOrderByDataOcorrenciaDesc(Integer projetoId);
}
