package com.leandrofita.generic_specification_builder_example.dto;

import com.leandrofita.generic_specification_builder_example.enums.PlayerRole;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PlayerFilterRespondeDTO {

    private Long id;
    private String name;
    private PlayerRole role;

    private PlayerProfileResponseDTO profile;

    private List<TableRpgResponseDTO> tables;
}
