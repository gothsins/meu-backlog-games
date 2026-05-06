package com.guiverme.meu_backlog_games.controller;

import com.guiverme.meu_backlog_games.dto.GameRequestDTO;
import com.guiverme.meu_backlog_games.dto.GameResponseDTO;
import com.guiverme.meu_backlog_games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jogos")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public List<GameResponseDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> cadastrar(@Valid @RequestBody GameRequestDTO dto) {
        GameResponseDTO jogoSalvo = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody GameRequestDTO dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pesquisa")
    public List<GameResponseDTO> pesquisar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String categoriaNome,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String plataforma) {
        return service.pesquisarJogos(titulo, categoriaNome, categoriaId, plataforma);
    }

    @GetMapping("/agrupados")
    public Map<String, List<GameResponseDTO>> listarAgrupados() {
        return service.listarJogosAgrupadosPorCategoria();
    }
}