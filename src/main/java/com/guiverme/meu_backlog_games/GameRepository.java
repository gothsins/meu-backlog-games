package com.guiverme.meu_backlog_games;

import com.guiverme.meu_backlog_games.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // Busca exata pela plataforma
    List<Game> findByPlataforma(String plataforma);

    // Busca por parte do título (ignora maiúsculas e minúsculas)
    List<Game> findByTituloContainingIgnoreCase(String titulo);

}