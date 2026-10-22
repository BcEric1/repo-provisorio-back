package com.kawz.tattoo.domain.repository;

import com.kawz.tattoo.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
    List<Sessao> findByProjetoIdOrderByDataHorarioAsc(Integer projetoId);
}
