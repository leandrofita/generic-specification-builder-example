package com.leandrofita.generic_specification_builder_example.mapper;

import com.leandrofita.generic_specification_builder_example.dto.TableRpgResponseDTO;
import com.leandrofita.generic_specification_builder_example.entity.TableRpg;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GameSessionMapper.class})
public interface TableRpgMapper {
    TableRpgResponseDTO toDto(TableRpg table);
}