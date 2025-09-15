package com.leandrofita.generic_specification_builder_example.dto;

import com.leandrofita.generic_specification_builder_example.enums.PlayerRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerFilterDTO {

    private String name;
    private PlayerRole role;
    private String email;
    private String nickname;

    private String tableName;
    private String adventureName;
    private LocalDate playedAtStart;
    private LocalDate playedAtEnd;
    private String gameTitle;
}
