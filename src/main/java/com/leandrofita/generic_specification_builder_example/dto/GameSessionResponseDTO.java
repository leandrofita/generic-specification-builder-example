package com.leandrofita.generic_specification_builder_example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GameSessionResponseDTO {

    private Long id;
    private String adventureName;
    private LocalDate playedAt;

    private GameResponseDTO game;
}
