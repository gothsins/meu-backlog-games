package com.guiverme.meu_backlog_games;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode estar vazio")
    private String titulo;
    private String plataforma;
    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 10, message = "A nota máxima é 10")
    private Integer nota;
    private String status;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @PastOrPresent(message = "A data de lançamento não pode ser no futuro")
    private LocalDate dataLancamento;
}