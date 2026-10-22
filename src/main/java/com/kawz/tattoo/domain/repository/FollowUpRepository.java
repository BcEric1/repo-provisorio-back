package com.kawz.tattoo.domain.repository;

import com.kawz.tattoo.domain.model.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {
    List<FollowUp> findByClienteIdOrderByDataPrevistaAsc(Integer clienteId);
}
