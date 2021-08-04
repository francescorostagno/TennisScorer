package com.tennisscorer.repository;

import com.tennisscorer.model.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long>{
    Boolean existsPlayerByPlayerName(String player_name);
    Player findByPlayerId(Long player_id);
    Player findByPlayerName(String player_name);
    @Modifying
    @Query(
            value = "select p.player_name from TennisMatchScoringApplication.player p where p.player_name LIKE %:term% LIMIT 50",
            nativeQuery = true

    )
    List<String> getPlayerTerm(String term);
}
