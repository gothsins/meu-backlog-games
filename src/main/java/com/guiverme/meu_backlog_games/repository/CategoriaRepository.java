package com.guiverme.meu_backlog_games.repository;

import com.guiverme.meu_backlog_games.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}