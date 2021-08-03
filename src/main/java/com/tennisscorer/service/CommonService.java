package com.tennisscorer.service;

import java.util.ArrayList;
import java.util.List;

import com.tennisscorer.dto.GoldenRegister;
import com.tennisscorer.dto.MatchStatistics;
import com.tennisscorer.dto.PlayerMatch;
import com.tennisscorer.dto.TourneyMatch;
import com.tennisscorer.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

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

    public List<Tourney> getTourneyByName(String tourney_name){
        return tourneyRepository.findAllByTourneyName(tourney_name);
    }

    public Player getPlayerByName(String player_name){
        return  playerRepository.findByPlayerName(player_name);
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

    public List<Ranking> getPlayerRanking(String player_name) {
        Player player = playerRepository.findByPlayerName(player_name);
        List<Ranking> ranking = null;
        if (player != null) {
            ranking = rankingRepository.findAllByPlayerId(player.getPlayerId());

        }
        return ranking;
    }

    public List<PlayerMatch> getPlayerMatch(String player_name){
        List<TennisMatch> tennisMatchesWinner = repository.findAllByWinnerName(player_name);
        List<PlayerMatch> allPlayerMatch = new ArrayList<>();
        if(tennisMatchesWinner != null){
            for( int i = 0; i < tennisMatchesWinner.size(); i ++){
                PlayerMatch playerMatch = new PlayerMatch(
                        tennisMatchesWinner.get(i).getTourney_name(),
                        tennisMatchesWinner.get(i).getScore(),
                        tennisMatchesWinner.get(i).getLoserName(),
                        1
                );
                allPlayerMatch.add(playerMatch);
            }
        }
        List<TennisMatch> tennisMatchesLoser = repository.findAllByLoserName(player_name);
        if(tennisMatchesLoser != null){
            for(int i = 0; i < tennisMatchesLoser.size(); i ++){
                PlayerMatch playerMatch = new PlayerMatch(
                        tennisMatchesLoser.get(i).getTourney_name(),
                        tennisMatchesLoser.get(i).getScore(),
                        tennisMatchesLoser.get(i).getWinnerName(),
                        0
                );

                allPlayerMatch.add(playerMatch);
            }
        }

        return allPlayerMatch;
    }

    public List<GoldenRegister> getTourneyGoldenRegister(String tourney_name){
            List<Tourney> tourneys = tourneyRepository.findAllByTourneyName(tourney_name);
            List<GoldenRegister> goldenRegisters = new ArrayList<>();
            if(!tourneys.isEmpty()){
                for(int i = 0; i < tourneys.size(); i++){
                    GoldenRegister goldenResister = new GoldenRegister(
                            tourney_name,
                            tourneys.get(i).getSurface(),
                            tourneys.get(i).getTourney_date(),
                            tourneys.get(i).getWinnerName(),
                            tourneys.get(i).getLoserName(),
                            playerRepository.findByPlayerName(tourneys.get(i).getWinnerName()),
                            playerRepository.findByPlayerName(tourneys.get(i).getLoserName())
                    );
                    goldenRegisters.add(goldenResister);
                }
            }

            return goldenRegisters;
    }

    public List<String> autocompletePlayer(String term){
        return playerRepository.getPlayerTerm(term);
    }


    public List<Tourney> getAllTourney(){
        return (List<Tourney>) tourneyRepository.findAll();
    }


    public List<Tourney> getAllPlayerWinnerTourney(String playerName){
        List<Tourney> winnerTourneys = tourneyRepository.findAllByWinnerPlayer(playerRepository.findByPlayerName(playerName));
        return winnerTourneys;
    }

    public List<TourneyMatch> getAllTourneyMatch(String tourney_id){
        List<TourneyMatch> tourneyMatches = new ArrayList<>();
        List<TennisMatch> tennisMatches = repository.findAllByTourneyId(tourney_id);
        if(!tennisMatches.isEmpty()){
            for (int i = 0; i < tennisMatches.size(); i++){
                TourneyMatch tourneyMatch = new TourneyMatch(
                        tennisMatches.get(i).getWinnerPlayer(),
                        tennisMatches.get(i).getLoserPlayer(),
                        tennisMatches.get(i).getScore(),
                        tennisMatches.get(i).getMatch_num(),
                        getMatchStatistics(tourney_id,tennisMatches.get(i).getMatch_num()),
                        tennisMatches.get(i).getTourney_date()
                );

                tourneyMatches.add(tourneyMatch);
            }
        }
        return tourneyMatches;
    }

}
