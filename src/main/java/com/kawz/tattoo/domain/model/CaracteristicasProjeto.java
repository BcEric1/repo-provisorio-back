package com.kawz.tattoo.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caracteristicas_projeto")
public class CaracteristicasProjeto extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false, unique = true)
    private Projeto projeto;
    @Column(name = "ideia_expectativa", columnDefinition = "text")
    private String ideiaExpectativa;
    @Column(name = "estilo_arte", length = 120)
    private String estiloArte;
    @Column(name = "localizacao_corpo", length = 120)
    private String localizacaoCorpo;
    @Column(length = 80)
    private String dificuldade;
    @Column(length = 120)
    private String cores;
    @Column(length = 80)
    private String complexidade;

    protected CaracteristicasProjeto() {}
    CaracteristicasProjeto(Projeto projeto) { this.projeto = projeto; }

    public void atualizarCaracteristicas(String ideiaExpectativa, String estiloArte, String localizacaoCorpo,
                                         String dificuldade, String cores, String complexidade) {
        this.ideiaExpectativa = ideiaExpectativa;
        this.estiloArte = estiloArte;
        this.localizacaoCorpo = localizacaoCorpo;
        this.dificuldade = dificuldade;
        this.cores = cores;
        this.complexidade = complexidade;
    }

    public String getIdeiaExpectativa() { return ideiaExpectativa; }
    public String getEstiloArte() { return estiloArte; }
    public String getLocalizacaoCorpo() { return localizacaoCorpo; }
    public String getDificuldade() { return dificuldade; }
    public String getCores() { return cores; }
    public String getComplexidade() { return complexidade; }
}
