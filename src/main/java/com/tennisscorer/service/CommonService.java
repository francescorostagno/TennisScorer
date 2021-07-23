package com.tennisscorer.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.tennisscorer.model.*;
import com.tennisscorer.repository.TennisRepository;
import com.tennisscorer.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Configurable
@Service
public class CommonService {

    @Autowired
    com.tennisscorer.repository.TennisRepository repository;
    @Autowired
    com.tennisscorer.repository.PlayerRepository playerRepository;
    @Autowired
    com.tennisscorer.repository.TourneyRepository tourneyRepository;

    @Autowired
    com.tennisscorer.repository.RankingRepository rankingRepository;

    public List<TennisMatch> getTourneyTennisMatch(String tourney_id){
       return repository.findAllByTourneyId(tourney_id);
    }

    public MatchStatistics getMatchStatistics(String tourney_id, Integer match_num){
        TennisMatch tennisMatch = repository.findByTourneyIdAndMatchNum(tourney_id,match_num);
        return new MatchStatistics(
                tennisMatch.getW_ace(),
                tennisMatch.getW_df(),
                tennisMatch.getW_svpt(),
                tennisMatch.getW_1st_in(),
                tennisMatch.getW_1st_won(),
                tennisMatch.getW_2nd_won(),
                tennisMatch.getW_sv_gms(),
                tennisMatch.getW_bp_saved(),
                tennisMatch.getW_bp_faced(),
                tennisMatch.getL_ace(),
                tennisMatch.getL_df(),
                tennisMatch.getL_svpt(),
                tennisMatch.getL_1st_in(),
                tennisMatch.getL_1st_won(),
                tennisMatch.getL_2nd_won(),
                tennisMatch.getL_sv_gms(),
                tennisMatch.getL_bp_saved(),
                tennisMatch.getL_bp_faced(),
                tennisMatch.getWinner_rank(),
                tennisMatch.getWinner_rank_points(),
                tennisMatch.getLoser_rank(),
                tennisMatch.getLoser_rank_points()
                );
    }

    public List<Ranking> getPlayerRanking(String player_name){
        Player player = playerRepository.findByPlayerName(player_name);
        List<Ranking>  ranking = null;
        if(player != null){
            ranking = rankingRepository.findAllByPlayerId(player.getPlayerId());

        }
        return ranking;
    }

    public List<Tourney> getAllTourney(){
        return (List<Tourney>) tourneyRepository.findAll();
    }
}
