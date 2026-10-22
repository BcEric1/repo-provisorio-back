package com.kawz.tattoo.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class ApiModels {
    private ApiModels() {}

    public record TatuadorCreate(
            @NotBlank @Size(max = 120) String nome,
            @NotBlank @Email @Size(max = 180) String email,
            @NotBlank @Size(min = 8, max = 72) String senha) {}

    public record TatuadorUpdate(
            @NotBlank @Size(max = 120) String nome,
            @NotBlank @Email @Size(max = 180) String email,
            @Size(min = 8, max = 72) String senha) {}

    public record LoginRequest(@NotBlank @Email String email, @NotBlank String senha) {}
    public record LoginResponse(boolean autenticado, Integer tatuadorId, String nome) {}
    public record TatuadorResponse(Integer id, String nome, String email) {}

    public record ClienteInput(
            @NotBlank @Size(max = 120) String nome,
            @Size(max = 30) String telefone,
            @Size(max = 14) String cpf,
            @Size(max = 100) String instagram,
            @Size(max = 80) String canalAquisicao,
            @Size(max = 500) String linkConversa,
            @Size(max = 500) String linkPasta,
            @NotBlank @Size(max = 40) String status) {}

    public record ClienteResponse(Integer id, String nome, String telefone, String cpf, String instagram,
                                  String canalAquisicao, String linkConversa, String linkPasta,
                                  String status, LocalDateTime dataCriacao) {}

    public record ProjetoInput(
            @NotNull Integer tatuadorId,
            @NotNull Integer clienteId,
            @NotBlank String descricao,
            @NotBlank @Size(max = 40) String statusProjeto,
            @NotNull @DecimalMin("0.00") BigDecimal valorTotal,
            @NotNull @DecimalMin("0.00") BigDecimal valorSinal,
            @NotNull @Min(1) Integer quantidadeSessoes,
            LocalDate dataValidade) {}

    public record ProjetoResponse(Integer id, Integer tatuadorId, Integer clienteId, String descricao,
                                  String statusProjeto, BigDecimal valorTotal, BigDecimal valorSinal,
                                  BigDecimal saldo, Integer quantidadeSessoes, LocalDate dataValidade) {}

    public record StatusRequest(@NotBlank @Size(max = 40) String status) {}
}
