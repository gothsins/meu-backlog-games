package com.guiverme.meu_backlog_games.controller;

import com.guiverme.meu_backlog_games.dto.CategoriaRequestDTO;
import com.guiverme.meu_backlog_games.dto.CategoriaResponseDTO;
import com.guiverme.meu_backlog_games.entity.Categoria;
import com.guiverme.meu_backlog_games.exception.ResourceNotFoundException;
import com.guiverme.meu_backlog_games.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        CategoriaResponseDTO categoria = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + id));

        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO categoriaAtualizada = service.atualizar(id, dto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para atualização. ID: " + id));

        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.deletar(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada para exclusão. ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}