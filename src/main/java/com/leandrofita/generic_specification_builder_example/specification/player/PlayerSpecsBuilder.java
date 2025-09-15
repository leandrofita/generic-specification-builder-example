package com.leandrofita.generic_specification_builder_example.specification.player;

import com.leandrofita.generic_specification_builder_example.dto.PlayerFilterDTO;
import com.leandrofita.generic_specification_builder_example.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerSpecsBuilder {

    @Autowired
    private PlayerSpecs specs;

    public Specification<Player> build(PlayerFilterDTO filter) {
        List<Specification<Player>> specsList = new ArrayList<>();

        Optional.ofNullable(specs.nameLike(filter.getName())).ifPresent(specsList::add);
        Optional.ofNullable(specs.roleEquals(filter.getRole())).ifPresent(specsList::add);
        Optional.ofNullable(specs.emailLike(filter.getEmail())).ifPresent(specsList::add);
        Optional.ofNullable(specs.nicknameLike(filter.getNickname())).ifPresent(specsList::add);
        Optional.ofNullable(specs.tableNameLike(filter.getTableName())).ifPresent(specsList::add);
        Optional.ofNullable(specs.adventureNameLike(filter.getAdventureName())).ifPresent(specsList::add);
        Optional.ofNullable(specs.playedAtBetween(filter.getPlayedAtStart(), filter.getPlayedAtEnd())).ifPresent(specsList::add);
        Optional.ofNullable(specs.gameTitleLike(filter.getGameTitle())).ifPresent(specsList::add);

        return specsList.isEmpty()
                ? (((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true))))
                : Specification.allOf(specsList);

    }
}
