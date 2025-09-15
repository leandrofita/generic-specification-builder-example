package com.leandrofita.generic_specification_builder_example.specification.player;

import com.leandrofita.generic_specification_builder_example.entity.Player;
import com.leandrofita.generic_specification_builder_example.enums.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.leandrofita.generic_specification_builder_example.specification.builder.DefaultSpecificationBuilderInterface;

import java.time.LocalDate;

@Component
public class PlayerSpecs {

    @Autowired
    private DefaultSpecificationBuilderInterface builder;

    public Specification<Player> nameLike(String name) {
        return builder.like("name", name);
    }

    public Specification<Player> roleEquals(PlayerRole role) {
        return builder.equals("role", role);
    }

    public Specification<Player> emailLike(String email) {
        return builder.nestedJoinLike("profile.email", email);
    }

    public Specification<Player> nicknameLike(String nickname) {
        return builder.nestedJoinLike("profile.nickname", nickname);
    }

    public Specification<Player> tableNameLike(String tableName) {
        return builder.nestedCollectionJoinLike("tables.name", tableName);
    }

    public Specification<Player> adventureNameLike(String adventureName) {
        return builder.nestedCollectionJoinLike("tables.sessions.adventureName", adventureName);
    }

    public Specification<Player> playedAtBetween(LocalDate startDate, LocalDate endDate) {
        return builder.betweenLocalDatesCollection("tables.sessions.playedAt", startDate, endDate);
    }

    public Specification<Player> gameTitleLike(String gameTitle) {
        return builder.nestedCollectionJoinLike("tables.sessions.game.title", gameTitle);
    }

}
