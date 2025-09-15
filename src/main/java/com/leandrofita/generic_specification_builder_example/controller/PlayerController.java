package com.leandrofita.generic_specification_builder_example.controller;

import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterDTO;
import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterRespondeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leandrofita.generic_specification_builder_example.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/filter")
    public ResponseEntity<Page<PlayerFilterRespondeDTO>> filterPlayers(
            @RequestParam(defaultValue = "0" ) int pageNumber,
            @RequestParam(defaultValue = "10" ) int pageSize,
            @RequestParam(defaultValue = "id" ) String sortBy,
            @RequestParam(defaultValue = "asc" ) String sortOrder,
            @RequestBody PlayerFilterDTO filter) {

        return ResponseEntity.ok(playerService.filterPlayers(
                pageNumber,
                pageSize,
                sortBy,
                sortOrder,
                filter));

    }

}
