package com.kawz.tattoo.domain.service;

import com.kawz.tattoo.api.dto.ApiModels.ClienteInput;
import com.kawz.tattoo.api.dto.ApiModels.ClienteResponse;
import com.kawz.tattoo.domain.exception.BusinessException;
import com.kawz.tattoo.domain.exception.ResourceNotFoundException;
import com.kawz.tattoo.domain.model.Cliente;
import com.kawz.tattoo.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kawz.tattoo.api.dto.ApiMapper.toResponse;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return repository.findAll().stream().map(com.kawz.tattoo.api.dto.ApiMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscar(Integer id) { return toResponse(obter(id)); }

    @Transactional
    public ClienteResponse criar(ClienteInput input) {
        String cpf = normalizarCpf(input.cpf());
        validarCpf(cpf, null);
        return toResponse(repository.save(new Cliente(input.nome().trim(), input.telefone(), cpf,
                input.instagram(), input.canalAquisicao(), input.linkConversa(), input.linkPasta(), input.status())));
    }

    @Transactional
    public ClienteResponse atualizar(Integer id, ClienteInput input) {
        Cliente cliente = obter(id);
        String cpf = normalizarCpf(input.cpf());
        validarCpf(cpf, id);
        cliente.atualizarCadastro(input.nome().trim(), input.telefone(), cpf, input.instagram(),
                input.canalAquisicao(), input.linkConversa(), input.linkPasta(), input.status());
        return toResponse(cliente);
    }

    @Transactional
    public void excluir(Integer id) { repository.delete(obter(id)); }

    private Cliente obter(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    private String normalizarCpf(String cpf) {
        return cpf == null || cpf.isBlank() ? null : cpf.replaceAll("\\D", "");
    }

    private void validarCpf(String cpf, Integer id) {
        if (cpf == null) return;
        if (cpf.length() != 11) throw new BusinessException("CPF deve possuir 11 dígitos");
        boolean existe = id == null ? repository.existsByCpf(cpf) : repository.existsByCpfAndIdNot(cpf, id);
        if (existe) throw new BusinessException("CPF já cadastrado");
    }
}
