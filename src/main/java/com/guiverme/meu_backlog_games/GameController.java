package com.guiverme.meu_backlog_games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Game cadastrar(@RequestBody Game novoJogo) {
        return repository.save(novoJogo);
    }
}