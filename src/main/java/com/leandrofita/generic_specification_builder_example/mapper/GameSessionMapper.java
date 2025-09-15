package com.leandrofita.generic_specification_builder_example.mapper;

import com.leandrofita.generic_specification_builder_example.dto.GameSessionResponseDTO;
import com.leandrofita.generic_specification_builder_example.entity.GameSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GameMapper.class})
public interface GameSessionMapper {

    GameSessionResponseDTO toDto(GameSession gameSession);
}
