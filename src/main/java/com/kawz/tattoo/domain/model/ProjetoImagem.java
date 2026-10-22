package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "projeto_imagem")
public class ProjetoImagem {
    @EmbeddedId
    private ProjetoImagemId id;
    @MapsId("imagemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "imagem_id", nullable = false)
    private Imagem imagem;
    @MapsId("projetoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
    @Column(nullable = false, length = 50)
    private String tipo;
    @Column(nullable = false, length = 20)
    private String ordem;

    protected ProjetoImagem() {}
    public ProjetoImagem(Imagem imagem, Projeto projeto, String tipo, String ordem) {
        this.id = new ProjetoImagemId(imagem.getId(), projeto.getId());
        this.imagem = imagem;
        this.projeto = projeto;
        this.tipo = tipo;
        this.ordem = ordem;
    }
    public void alterarOrdem(String novaOrdem) { this.ordem = novaOrdem; }
    public void atualizar(String tipo, String ordem) { this.tipo = tipo; this.ordem = ordem; }
    public ProjetoImagemId getId() { return id; }
    public Imagem getImagem() { return imagem; }
    public Projeto getProjeto() { return projeto; }
    public String getTipo() { return tipo; }
    public String getOrdem() { return ordem; }
}
