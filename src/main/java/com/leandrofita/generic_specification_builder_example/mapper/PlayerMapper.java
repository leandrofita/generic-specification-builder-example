package com.leandrofita.generic_specification_builder_example.mapper;

import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterRespondeDTO;
import com.leandrofita.generic_specification_builder_example.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TableRpgMapper.class, PlayerProfileMapper.class})
public interface PlayerMapper {

    PlayerFilterRespondeDTO toPlayerFilterRespondeDTO(Player player);
}
