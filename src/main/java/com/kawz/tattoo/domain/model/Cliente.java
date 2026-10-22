package com.kawz.tattoo.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(length = 30)
    private String telefone;
    @Column(unique = true, length = 14)
    private String cpf;
    @Column(length = 100)
    private String instagram;
    @Column(name = "canal_aquisicao", length = 80)
    private String canalAquisicao;
    @Column(name = "link_conversa", length = 500)
    private String linkConversa;
    @Column(name = "link_pasta", length = 500)
    private String linkPasta;
    @Column(nullable = false, length = 40)
    private String status;
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Anamnese anamnese;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PreferenciasCliente preferencias;

    protected Cliente() {}

    public Cliente(String nome, String telefone, String cpf, String instagram, String canalAquisicao,
                   String linkConversa, String linkPasta, String status) {
        atualizarCadastro(nome, telefone, cpf, instagram, canalAquisicao, linkConversa, linkPasta, status);
        this.anamnese = new Anamnese(this);
        this.preferencias = new PreferenciasCliente(this);
    }

    @PrePersist
    void prePersist() {
        if (dataCriacao == null) dataCriacao = LocalDateTime.now();
    }

    public void atualizarCadastro(String nome, String telefone, String cpf, String instagram,
                                  String canalAquisicao, String linkConversa, String linkPasta, String status) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.instagram = instagram;
        this.canalAquisicao = canalAquisicao;
        this.linkConversa = linkConversa;
        this.linkPasta = linkPasta;
        this.status = status;
    }

    public void converterEmCliente() { this.status = "CLIENTE"; }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public String getInstagram() { return instagram; }
    public String getCanalAquisicao() { return canalAquisicao; }
    public String getLinkConversa() { return linkConversa; }
    public String getLinkPasta() { return linkPasta; }
    public String getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public Anamnese getAnamnese() { return anamnese; }
    public PreferenciasCliente getPreferencias() { return preferencias; }
}
