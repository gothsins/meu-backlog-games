package com.guiverme.meu_backlog_games.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDTO {
    private Long id;
    private String titulo;
    private String plataforma;
    private Integer nota;
    private String status;

    private String nomeCategoria;
}