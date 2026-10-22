package com.kawz.tattoo.domain.service;

import com.kawz.tattoo.api.dto.ApiModels.ProjetoInput;
import com.kawz.tattoo.api.dto.ApiModels.ProjetoResponse;
import com.kawz.tattoo.api.dto.ApiModels.StatusRequest;
import com.kawz.tattoo.domain.exception.BusinessException;
import com.kawz.tattoo.domain.exception.ResourceNotFoundException;
import com.kawz.tattoo.domain.model.Cliente;
import com.kawz.tattoo.domain.model.Projeto;
import com.kawz.tattoo.domain.model.Tatuador;
import com.kawz.tattoo.domain.repository.ClienteRepository;
import com.kawz.tattoo.domain.repository.ProjetoRepository;
import com.kawz.tattoo.domain.repository.TatuadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kawz.tattoo.api.dto.ApiMapper.toResponse;

@Service
public class ProjetoService {
    private final ProjetoRepository projetos;
    private final ClienteRepository clientes;
    private final TatuadorRepository tatuadores;

    public ProjetoService(ProjetoRepository projetos, ClienteRepository clientes, TatuadorRepository tatuadores) {
        this.projetos = projetos;
        this.clientes = clientes;
        this.tatuadores = tatuadores;
    }

    @Transactional(readOnly = true)
    public List<ProjetoResponse> listar() {
        return projetos.findAll().stream().map(com.kawz.tattoo.api.dto.ApiMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProjetoResponse buscar(Integer id) { return toResponse(obter(id)); }

    @Transactional
    public ProjetoResponse criar(ProjetoInput input) {
        validarValores(input);
        Projeto projeto = new Projeto(obterTatuador(input.tatuadorId()), obterCliente(input.clienteId()),
                input.descricao(), input.statusProjeto(), input.valorTotal(), input.valorSinal(),
                input.quantidadeSessoes(), input.dataValidade());
        return toResponse(projetos.save(projeto));
    }

    @Transactional
    public ProjetoResponse atualizar(Integer id, ProjetoInput input) {
        validarValores(input);
        Projeto projeto = obter(id);
        projeto.vincular(obterTatuador(input.tatuadorId()), obterCliente(input.clienteId()));
        projeto.atualizar(input.descricao(), input.statusProjeto(), input.valorTotal(), input.valorSinal(),
                input.quantidadeSessoes(), input.dataValidade());
        return toResponse(projeto);
    }

    @Transactional
    public ProjetoResponse alterarStatus(Integer id, StatusRequest input) {
        Projeto projeto = obter(id);
        projeto.alterarStatus(input.status());
        return toResponse(projeto);
    }

    @Transactional
    public void excluir(Integer id) { projetos.delete(obter(id)); }

    private void validarValores(ProjetoInput input) {
        if (input.valorSinal().compareTo(input.valorTotal()) > 0) {
            throw new BusinessException("O sinal não pode ser maior que o valor total");
        }
    }

    private Projeto obter(Integer id) {
        return projetos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto", id));
    }

    private Cliente obterCliente(Integer id) {
        return clientes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    private Tatuador obterTatuador(Integer id) {
        return tatuadores.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tatuador", id));
    }
}
