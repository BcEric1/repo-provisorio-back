package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "imagem")
public class Imagem extends BaseEntity {
    @Column(name = "public_id", nullable = false, unique = true, length = 255)
    private String publicId;
    @Column(nullable = false, length = 30)
    private String formato;
    @Column(nullable = false)
    private LocalDateTime imagemcol;

    protected Imagem() {}
    public Imagem(String publicId, String formato) { this.publicId = publicId; this.formato = formato; }
    @PrePersist
    void prePersist() { if (imagemcol == null) imagemcol = LocalDateTime.now(); }
    public String obterPublicId() { return publicId; }
    public void atualizar(String publicId, String formato) { this.publicId = publicId; this.formato = formato; }
    public String getPublicId() { return publicId; }
    public String getFormato() { return formato; }
    public LocalDateTime getImagemcol() { return imagemcol; }
}
