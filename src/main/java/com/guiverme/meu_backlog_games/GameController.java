package com.guiverme.meu_backlog_games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;


@RestController
@RequestMapping("/jogos")
public class GameController {

    @Autowired
    private GameRepository repository;

    @GetMapping
    public List<Game> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> cadastrar(@RequestBody Game novoJogo) {
        Game jogoSalvo = repository.save(novoJogo);

        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> atualizar(@PathVariable Long id, @RequestBody Game jogoAtualizado) {
        return repository.findById(id).map(game -> {
            game.setTitulo(jogoAtualizado.getTitulo());
            game.setPlataforma(jogoAtualizado.getPlataforma());
            game.setNota(jogoAtualizado.getNota());
            game.setStatus(jogoAtualizado.getStatus());
            return ResponseEntity.ok(repository.save(game));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(game -> ResponseEntity.ok(game))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}