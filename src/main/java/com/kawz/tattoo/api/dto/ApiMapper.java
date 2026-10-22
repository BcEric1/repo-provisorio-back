package com.kawz.tattoo.api.dto;

import com.kawz.tattoo.domain.model.Cliente;
import com.kawz.tattoo.domain.model.Projeto;
import com.kawz.tattoo.domain.model.Tatuador;

import static com.kawz.tattoo.api.dto.ApiModels.*;

public final class ApiMapper {
    private ApiMapper() {}

    public static TatuadorResponse toResponse(Tatuador value) {
        return new TatuadorResponse(value.getId(), value.getNome(), value.getEmail());
    }

    public static ClienteResponse toResponse(Cliente value) {
        return new ClienteResponse(value.getId(), value.getNome(), value.getTelefone(), value.getCpf(),
                value.getInstagram(), value.getCanalAquisicao(), value.getLinkConversa(), value.getLinkPasta(),
                value.getStatus(), value.getDataCriacao());
    }

    public static ProjetoResponse toResponse(Projeto value) {
        return new ProjetoResponse(value.getId(), value.getTatuador().getId(), value.getCliente().getId(),
                value.getDescricao(), value.getStatusProjeto(), value.getValorTotal(), value.getValorSinal(),
                value.calcularSaldo(), value.getQuantidadeSessoes(), value.getDataValidade());
    }
}
