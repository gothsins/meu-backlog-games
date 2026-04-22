package com.guiverme.meu_backlog_games;

import com.guiverme.meu_backlog_games.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}