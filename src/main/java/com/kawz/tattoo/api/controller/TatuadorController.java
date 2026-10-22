package com.kawz.tattoo.api.controller;

import com.kawz.tattoo.api.dto.ApiModels.*;
import com.kawz.tattoo.domain.service.TatuadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tatuadores")
public class TatuadorController {
    private final TatuadorService service;

    public TatuadorController(TatuadorService service) { this.service = service; }

    @GetMapping
    public List<TatuadorResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public TatuadorResponse buscar(@PathVariable Integer id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TatuadorResponse criar(@Valid @RequestBody TatuadorCreate input) { return service.criar(input); }

    @PutMapping("/{id}")
    public TatuadorResponse atualizar(@PathVariable Integer id, @Valid @RequestBody TatuadorUpdate input) {
        return service.atualizar(id, input);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) { service.excluir(id); }

    @PostMapping("/autenticacao")
    public LoginResponse autenticar(@Valid @RequestBody LoginRequest input) { return service.autenticar(input); }
}
