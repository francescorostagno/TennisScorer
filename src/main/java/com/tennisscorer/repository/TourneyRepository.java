package com.tennisscorer.repository;

import com.tennisscorer.model.Tourney;
import org.springframework.data.repository.CrudRepository;

public interface TourneyRepository extends CrudRepository<Tourney, Long> {
    Boolean existsTourneyByTourneyId(String tourney_id);
    Tourney findTourneyByTourneyId(String tourney_id);

}
