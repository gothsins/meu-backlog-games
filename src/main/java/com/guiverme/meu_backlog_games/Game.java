package com.guiverme.meu_backlog_games;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String plataforma;
    private Integer nota;
    private String status;     // Ex: "Backlog", "Jogando", "Zerado"
}