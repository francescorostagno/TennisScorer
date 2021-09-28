package com.tennisscorer.repository;

import com.tennisscorer.model.Ranking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RankingRepository extends CrudRepository<Ranking,Long> {
    Boolean existsByPlayerIdAndRankingDate(Long player_id, String ranking_date);
    List<Ranking> findAllByPlayerId(Long player_id);
    List<Ranking> findAllByRankingDate(String ranking_date);
    List<Ranking> findByRankingDateBetweenOrderByRankingDateDesc(String date_start,String date_end);
}
