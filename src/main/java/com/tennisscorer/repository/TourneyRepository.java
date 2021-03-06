package com.tennisscorer.repository;

import com.tennisscorer.model.Player;
import com.tennisscorer.model.Tourney;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourneyRepository extends CrudRepository<Tourney, Long> {
    Boolean existsTourneyByTourneyId(String tourney_id);
    Tourney findTourneyByTourneyId(String tourney_id);
    List<Tourney> findAllByTourneyName(String tourney_name);
    List<Tourney> findAllByWinnerPlayer(Player winnerPlayer);
    List<Tourney> findByTourneyDateBetweenOrderByTourneyDateDesc(String date_start,String date_end);
}
