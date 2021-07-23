package com.tennisscorer.repository;
import com.tennisscorer.model.Player;

import com.tennisscorer.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long>{
    Boolean existsPlayerByPlayerName(String player_name);
    Player findByPlayerId(Long player_id);
    Player findByPlayerName(String player_name);
}
