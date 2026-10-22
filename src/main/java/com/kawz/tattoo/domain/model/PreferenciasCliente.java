package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "preferencias_cliente")
public class PreferenciasCliente extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;
    @Column(name = "musica_favorita", length = 150)
    private String musicaFavorita;
    @Column(name = "comida_favorita", length = 150)
    private String comidaFavorita;
    @Column(name = "estilo_tatuagem_favorita", length = 150)
    private String estiloTatuagemFavorita;
    @Column(name = "faixa_media_orcamento", precision = 12, scale = 2)
    private BigDecimal faixaMediaOrcamento;
    @Column(columnDefinition = "text")
    private String habitos;
    @Column(name = "disponibilidade_horarios", columnDefinition = "text")
    private String disponibilidadeHorarios;
    @Column(name = "observacoes_gerais", columnDefinition = "text")
    private String observacoesGerais;

    protected PreferenciasCliente() {}
    PreferenciasCliente(Cliente cliente) { this.cliente = cliente; }

    public void atualizarPreferencias(String musicaFavorita, String comidaFavorita, String estiloTatuagemFavorita,
                                      BigDecimal faixaMediaOrcamento, String habitos,
                                      String disponibilidadeHorarios, String observacoesGerais) {
        this.musicaFavorita = musicaFavorita;
        this.comidaFavorita = comidaFavorita;
        this.estiloTatuagemFavorita = estiloTatuagemFavorita;
        this.faixaMediaOrcamento = faixaMediaOrcamento;
        this.habitos = habitos;
        this.disponibilidadeHorarios = disponibilidadeHorarios;
        this.observacoesGerais = observacoesGerais;
    }

    public String getMusicaFavorita() { return musicaFavorita; }
    public String getComidaFavorita() { return comidaFavorita; }
    public String getEstiloTatuagemFavorita() { return estiloTatuagemFavorita; }
    public BigDecimal getFaixaMediaOrcamento() { return faixaMediaOrcamento; }
    public String getHabitos() { return habitos; }
    public String getDisponibilidadeHorarios() { return disponibilidadeHorarios; }
    public String getObservacoesGerais() { return observacoesGerais; }
}
