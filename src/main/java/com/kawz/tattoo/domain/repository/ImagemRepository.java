package com.kawz.tattoo.domain.repository;

import com.kawz.tattoo.domain.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    boolean existsByPublicId(String publicId);
    boolean existsByPublicIdAndIdNot(String publicId, Integer id);
}
