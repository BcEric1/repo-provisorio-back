package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contrato")
public class Contrato extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false, unique = true)
    private Projeto projeto;
    @Column(name = "limite_alteracoes_arte", length = 120)
    private String limiteAlteracoesArte;
    @Column(name = "tolerancia_atraso", length = 120)
    private String toleranciaAtraso;
    @Column(name = "uso_imagem", length = 120)
    private String usoImagem;
    @Column(name = "data_assinatura")
    private LocalDate dataAssinatura;
    @Column(name = "status_contrato", nullable = false, length = 40)
    private String statusContrato = "PENDENTE";
    @Column(name = "pdf_url", length = 500)
    private String pdfUrl;
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    @Column(name = "transaction_ref", length = 150)
    private String transactionRef;

    protected Contrato() {}
    Contrato(Projeto projeto) { this.projeto = projeto; }

    @PrePersist
    void prePersist() { if (criadoEm == null) criadoEm = LocalDateTime.now(); }

    public void atualizar(String limiteAlteracoesArte, String toleranciaAtraso, String usoImagem,
                          String statusContrato, String pdfUrl, String transactionRef) {
        this.limiteAlteracoesArte = limiteAlteracoesArte;
        this.toleranciaAtraso = toleranciaAtraso;
        this.usoImagem = usoImagem;
        this.statusContrato = statusContrato;
        this.pdfUrl = pdfUrl;
        this.transactionRef = transactionRef;
    }
    public void registrarAssinatura(LocalDate data) {
        this.dataAssinatura = data;
        this.statusContrato = "ASSINADO";
    }
    public String obterPdfUrl() { return pdfUrl; }

    public String getLimiteAlteracoesArte() { return limiteAlteracoesArte; }
    public String getToleranciaAtraso() { return toleranciaAtraso; }
    public String getUsoImagem() { return usoImagem; }
    public LocalDate getDataAssinatura() { return dataAssinatura; }
    public String getStatusContrato() { return statusContrato; }
    public String getPdfUrl() { return pdfUrl; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public String getTransactionRef() { return transactionRef; }
}
