package com.kawz.tattoo.domain.repository;

import com.kawz.tattoo.domain.model.ProjetoImagem;
import com.kawz.tattoo.domain.model.ProjetoImagemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoImagemRepository extends JpaRepository<ProjetoImagem, ProjetoImagemId> {
    List<ProjetoImagem> findByProjetoIdOrderByOrdemAsc(Integer projetoId);
}
