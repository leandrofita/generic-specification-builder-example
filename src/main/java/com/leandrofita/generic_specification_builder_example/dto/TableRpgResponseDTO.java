package com.leandrofita.generic_specification_builder_example.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TableRpgResponseDTO {

    private Long id;
    private String name;

    private List<GameSessionResponseDTO> sessions;
}
