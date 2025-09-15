package com.leandrofita.generic_specification_builder_example.mapper;

import com.leandrofita.generic_specification_builder_example.dto.GameResponseDTO;
import com.leandrofita.generic_specification_builder_example.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameResponseDTO toDto(Game game);
}
