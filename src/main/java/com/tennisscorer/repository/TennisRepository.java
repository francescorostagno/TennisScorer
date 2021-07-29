package com.tennisscorer.repository;

import com.tennisscorer.model.TennisMatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public  interface TennisRepository extends CrudRepository<TennisMatch, Long> {
    Boolean existsTennisMatchByTourneyIdAndMatchNum(String tourney_id, int match_num);
    List<TennisMatch> findAllByTourneyId(String tourney_id);
    TennisMatch findByTourneyIdAndMatchNum(String tourney_id, int match_num);
    List<TennisMatch> findAllByWinnerName(String winner_name);
    List<TennisMatch> findAllByLoserName(String looser_name);
}
