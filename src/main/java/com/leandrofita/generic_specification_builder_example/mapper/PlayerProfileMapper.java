package com.leandrofita.generic_specification_builder_example.mapper;

import com.leandrofita.generic_specification_builder_example.dto.PlayerProfileResponseDTO;
import com.leandrofita.generic_specification_builder_example.entity.PlayerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerProfileMapper {

    PlayerProfileResponseDTO toDto(PlayerProfile playerProfile);
}
