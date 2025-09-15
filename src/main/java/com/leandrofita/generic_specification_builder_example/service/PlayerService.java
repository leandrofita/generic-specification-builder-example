package com.leandrofita.generic_specification_builder_example.service;

import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterDTO;
import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterRespondeDTO;
import com.leandrofita.generic_specification_builder_example.entity.Player;
import com.leandrofita.generic_specification_builder_example.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.leandrofita.generic_specification_builder_example.repository.PlayerRepository;
import com.leandrofita.generic_specification_builder_example.specification.player.PlayerSpecsBuilder;
import com.leandrofita.generic_specification_builder_example.utils.PageableUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PageableUtils pageableUtils;

    @Autowired
    private PlayerSpecsBuilder specsBuilder;

    @Autowired
    private PlayerMapper mapper;

    @Transactional
    public Page<PlayerFilterRespondeDTO> filterPlayers(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortOrder,
            PlayerFilterDTO filter) {

        Pageable pageable = pageableUtils.getPageable(pageNumber, pageSize, sortBy, sortOrder);

        Specification<Player> spec = specsBuilder.build(filter);

        Page<Player> pagedPlayers = playerRepository.findAll(spec, pageable);

        Page<PlayerFilterRespondeDTO> map = pagedPlayers.map(mapper::toPlayerFilterRespondeDTO);

        return map;
    }
}
