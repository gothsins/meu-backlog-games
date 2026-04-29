package com.guiverme.meu_backlog_games;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jogos")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public List<Game> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Game> cadastrar(@Valid @RequestBody Game novoJogo) {
        Game jogoSalvo = service.cadastrar(novoJogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> atualizar(@PathVariable Long id, @Valid @RequestBody Game jogoAtualizado) {
        return service.atualizar(id, jogoAtualizado)
                .map(game -> ResponseEntity.ok(game))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(game -> ResponseEntity.ok(game))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pesquisa")
    public List<Game> pesquisar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String categoriaNome,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String plataforma) {
        return service.pesquisarJogos(titulo, categoriaNome, categoriaId, plataforma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}