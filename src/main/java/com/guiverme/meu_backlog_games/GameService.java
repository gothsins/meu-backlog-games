package com.guiverme.meu_backlog_games;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public List<Game> listarTodos() {
        return repository.findAll();
    }

    public List<Game> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNomeIgnoreCase(categoria);
    }

    public Game cadastrar(Game novoJogo) {
        return repository.save(novoJogo);
    }

    public Optional<Game> atualizar(Long id, Game jogoAtualizado) {
        return repository.findById(id).map(game -> {
            game.setTitulo(jogoAtualizado.getTitulo());
            game.setPlataforma(jogoAtualizado.getPlataforma());
            game.setNota(jogoAtualizado.getNota());
            game.setStatus(jogoAtualizado.getStatus());
            game.setDataLancamento(jogoAtualizado.getDataLancamento());
            return repository.save(game);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Game> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Game> pesquisarJogos(String titulo, String categoriaNome, Long categoriaID, String plataforma) {
        if (titulo != null && !titulo.isEmpty()) {
            return repository.findByTituloContainingIgnoreCase(titulo);
        }

        if (categoriaNome != null && !categoriaNome.isEmpty()) {
            return repository.findByCategoriaNomeIgnoreCase(categoriaNome);
        }

        if (categoriaID != null) {
            return repository.findByCategoriaId(categoriaID);
        }

        if (plataforma != null && !plataforma.isEmpty()) {
            return repository.findByPlataformaIgnoreCase(plataforma);
        }

        return repository.findAll();
    }



}
