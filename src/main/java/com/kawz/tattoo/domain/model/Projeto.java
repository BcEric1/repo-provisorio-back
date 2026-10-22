package com.kawz.tattoo.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
public class Projeto extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tatuador_id", nullable = false)
    private Tatuador tatuador;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @Column(nullable = false, columnDefinition = "text")
    private String descricao;
    @Column(name = "status_projeto", nullable = false, length = 40)
    private String statusProjeto;
    @Column(name = "valor_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;
    @Column(name = "valor_sinal", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorSinal;
    @Column(name = "quantidade_sessoes", nullable = false)
    private Integer quantidadeSessoes;
    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @OneToOne(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CaracteristicasProjeto caracteristicas;
    @OneToOne(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Contrato contrato;

    protected Projeto() {}

    public Projeto(Tatuador tatuador, Cliente cliente, String descricao, String statusProjeto,
                   BigDecimal valorTotal, BigDecimal valorSinal, Integer quantidadeSessoes, LocalDate dataValidade) {
        this.tatuador = tatuador;
        this.cliente = cliente;
        atualizar(descricao, statusProjeto, valorTotal, valorSinal, quantidadeSessoes, dataValidade);
        this.caracteristicas = new CaracteristicasProjeto(this);
        this.contrato = new Contrato(this);
    }

    public void atualizar(String descricao, String statusProjeto, BigDecimal valorTotal, BigDecimal valorSinal,
                          Integer quantidadeSessoes, LocalDate dataValidade) {
        this.descricao = descricao;
        this.statusProjeto = statusProjeto;
        this.valorTotal = valorTotal;
        this.valorSinal = valorSinal;
        this.quantidadeSessoes = quantidadeSessoes;
        this.dataValidade = dataValidade;
    }

    public BigDecimal calcularSaldo() { return valorTotal.subtract(valorSinal); }
    public void alterarStatus(String novoStatus) { this.statusProjeto = novoStatus; }
    public void vincular(Tatuador tatuador, Cliente cliente) {
        this.tatuador = tatuador;
        this.cliente = cliente;
    }

    public Tatuador getTatuador() { return tatuador; }
    public Cliente getCliente() { return cliente; }
    public String getDescricao() { return descricao; }
    public String getStatusProjeto() { return statusProjeto; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public BigDecimal getValorSinal() { return valorSinal; }
    public Integer getQuantidadeSessoes() { return quantidadeSessoes; }
    public LocalDate getDataValidade() { return dataValidade; }
    public CaracteristicasProjeto getCaracteristicas() { return caracteristicas; }
    public Contrato getContrato() { return contrato; }
}
