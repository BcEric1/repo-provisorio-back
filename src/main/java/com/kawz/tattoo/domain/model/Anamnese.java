package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anamnese")
public class Anamnese extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;
    @Column(name = "dados_saude", columnDefinition = "text")
    private String dadosSaude;
    @Column(name = "preparacao_tatuagem", columnDefinition = "text")
    private String preparacaoTatuagem;

    protected Anamnese() {}
    Anamnese(Cliente cliente) { this.cliente = cliente; }

    public void atualizarInformacoes(String dadosSaude, String preparacaoTatuagem) {
        this.dadosSaude = dadosSaude;
        this.preparacaoTatuagem = preparacaoTatuagem;
    }

    public String getDadosSaude() { return dadosSaude; }
    public String getPreparacaoTatuagem() { return preparacaoTatuagem; }
}
