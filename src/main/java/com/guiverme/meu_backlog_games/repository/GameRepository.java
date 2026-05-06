package com.guiverme.meu_backlog_games.repository;

import com.guiverme.meu_backlog_games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByPlataformaIgnoreCase(String plataforma);

    List<Game> findByTituloContainingIgnoreCase(String titulo);

    List<Game> findByCategoriaId(Long id);

    List<Game> findByCategoriaNomeIgnoreCase(String nome);

}