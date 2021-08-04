package com.tennisscorer.repository;


import com.tennisscorer.model.Statistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Long> {
    Boolean existsStatisticsByTourneyIdAndMatchNum(String tourney_id, int match_num);
    List<Statistics> findAllByTourneyId(String tourney_id);
    Statistics findByTourneyIdAndMatchNum(String tourney_id, int match_num);
    List<Statistics> findAllByWinnerName(String winner_name);
    List<Statistics> findAllByLoserName(String looser_name);

}
