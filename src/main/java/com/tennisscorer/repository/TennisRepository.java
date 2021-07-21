package com.tennisscorer.repository;

import com.tennisscorer.model.TennisMatch;
import org.springframework.data.repository.CrudRepository;

public  interface TennisRepository extends CrudRepository<TennisMatch, Long> {
    Boolean existsTennisMatchByTourneyIdAndMatchNum(String tourney_id, int match_num);
}
