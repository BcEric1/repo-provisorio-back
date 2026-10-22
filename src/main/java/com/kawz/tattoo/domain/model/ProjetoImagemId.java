package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjetoImagemId implements Serializable {
    @Column(name = "imagem_id")
    private Integer imagemId;
    @Column(name = "projeto_id")
    private Integer projetoId;

    protected ProjetoImagemId() {}
    public ProjetoImagemId(Integer imagemId, Integer projetoId) {
        this.imagemId = imagemId;
        this.projetoId = projetoId;
    }
    public Integer getImagemId() { return imagemId; }
    public Integer getProjetoId() { return projetoId; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjetoImagemId that)) return false;
        return Objects.equals(imagemId, that.imagemId) && Objects.equals(projetoId, that.projetoId);
    }
    @Override public int hashCode() { return Objects.hash(imagemId, projetoId); }
}
