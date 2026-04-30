package com.guiverme.meu_backlog_games.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class GameRequestDTO {

    @NotBlank(message = "O título é obrigatório!")
    private String titulo;

    private String plataforma;
    private Integer nota;
    private String status;
    private LocalDate dataLancamento;

    private Long categoriaId;
}