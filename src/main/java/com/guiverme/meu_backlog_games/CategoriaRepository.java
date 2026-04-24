package com.guiverme.meu_backlog_games;

import com.guiverme.meu_backlog_games.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}