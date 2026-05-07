package com.guiverme.meu_backlog_games.controller;

import com.guiverme.meu_backlog_games.dto.GameRequestDTO;
import com.guiverme.meu_backlog_games.dto.GameResponseDTO;
import com.guiverme.meu_backlog_games.exception.ResourceNotFoundException;
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
        GameResponseDTO jogo = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID: " + id));

        return ResponseEntity.ok(jogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody GameRequestDTO dto) {
        GameResponseDTO jogoAtualizado = service.atualizar(id, dto)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado para atualização. ID: " + id));

        return ResponseEntity.ok(jogoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.deletar(id)) {
            throw new ResourceNotFoundException("Jogo não encontrado para exclusão. ID: " + id);
        }

        return ResponseEntity.noContent().build();
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