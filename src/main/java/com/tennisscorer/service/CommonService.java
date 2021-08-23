package com.tennisscorer.service;

import com.tennisscorer.dto.*;
import com.tennisscorer.helper.CommonHelper;
import com.tennisscorer.model.Player;
import com.tennisscorer.model.Ranking;
import com.tennisscorer.model.Statistics;
import com.tennisscorer.model.Tourney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Configurable
@Service
public class CommonService {

    @Autowired
    com.tennisscorer.repository.PlayerRepository playerRepository;
    @Autowired
    com.tennisscorer.repository.TourneyRepository tourneyRepository;
    @Autowired
    com.tennisscorer.repository.StatisticsRepository statisticsRepository;
    @Autowired
    com.tennisscorer.repository.RankingRepository rankingRepository;

    public List<Tourney> getTourneyByName(String tourney_name){
        return tourneyRepository.findAllByTourneyName(tourney_name);
    }

    public Player getPlayerByName(String player_name){
        return  playerRepository.findByPlayerName(player_name);
    }

    public MatchStatistics getMatchStatistics(String tourney_id, Integer match_num){
        Statistics tennisMatch = statisticsRepository.findByTourneyIdAndMatchNum(tourney_id,match_num);
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
        List<Statistics> tennisMatchesWinner = statisticsRepository.findAllByWinnerName(player_name);
        List<PlayerMatch> allPlayerMatch = new ArrayList<>();
        if(tennisMatchesWinner != null){
            for( int i = 0; i < tennisMatchesWinner.size(); i ++){
                PlayerMatch playerMatch = new PlayerMatch(
                        tennisMatchesWinner.get(i).getTourney().getTourneyName(),
                        tennisMatchesWinner.get(i).getScore(),
                        tennisMatchesWinner.get(i).getLoserName(),
                        1
                );
                allPlayerMatch.add(playerMatch);
            }
        }
        List<Statistics> tennisMatchesLoser = statisticsRepository.findAllByLoserName(player_name);
        if(tennisMatchesLoser != null){
            for(int i = 0; i < tennisMatchesLoser.size(); i ++){
                PlayerMatch playerMatch = new PlayerMatch(
                        tennisMatchesLoser.get(i).getTourney().getTourneyName(),
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
                            tourneys.get(i).getWinnerPlayer(),
                            tourneys.get(i).getLoserPlayer()
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
        List<Statistics> tennisMatches = statisticsRepository.findAllByTourneyId(tourney_id);
        if(!tennisMatches.isEmpty()){
            for (int i = 0; i < tennisMatches.size(); i++){
                TourneyMatch  tourneyMatch = new TourneyMatch(
                        tennisMatches.get(i).getWinnerPlayer(),
                        tennisMatches.get(i).getLoserPlayer(),
                        tennisMatches.get(i).getScore(),
                        tennisMatches.get(i).getMatchNum(),
                        getMatchStatistics(tourney_id,tennisMatches.get(i).getMatchNum()),
                        tennisMatches.get(i).getTourney().getTourney_date()
                );

                tourneyMatches.add(tourneyMatch);
            }
        }
        return tourneyMatches;
    }

    public List<Match> getAllMatch(){
        List<Statistics> statisticsList = (List<Statistics>) statisticsRepository.findAll();
        List<Match> matchList = new ArrayList<>();
        if( !statisticsList.isEmpty()){
            for(int i = 0; i < statisticsList.size(); i ++){
                Tourney tourney = statisticsList.get(i).getTourney();
                Match match = new Match(
                        statisticsList.get(i).getTourneyId(),
                        tourney.getTourneyName(),
                        tourney.getSurface(),
                        tourney.getDraw_size(),
                        tourney.getLevel(),
                        tourney.getTourney_date(),
                        statisticsList.get(i).getMatchNum(),
                        statisticsList.get(i).getWinner_id(),
                        statisticsList.get(i).getWinner_seed(),
                        statisticsList.get(i).getWinner_entry(),
                        statisticsList.get(i).getWinnerName(),
                        statisticsList.get(i).getWinner_hand(),
                        statisticsList.get(i).getWinner_ht(),
                        statisticsList.get(i).getWinner_ioc(),
                        statisticsList.get(i).getWinner_age(),
                        statisticsList.get(i).getLoser_id(),
                        statisticsList.get(i).getLoser_seed(),
                        statisticsList.get(i).getLoser_entry(),
                        statisticsList.get(i).getLoserName(),
                        statisticsList.get(i).getLoser_hand(),
                        statisticsList.get(i).getLoser_ioc(),
                        statisticsList.get(i).getLoser_ht(),
                        statisticsList.get(i).getLoser_age(),
                        statisticsList.get(i).getScore(),
                        statisticsList.get(i).getBest_of(),
                        statisticsList.get(i).getRound(),
                        statisticsList.get(i).getMinutes(),
                        statisticsList.get(i).getW_ace(),
                        statisticsList.get(i).getW_df(),
                        statisticsList.get(i).getW_svpt(),
                        statisticsList.get(i).getW_1st_in(),
                        statisticsList.get(i).getW_1st_won(),
                        statisticsList.get(i).getW_2nd_won(),
                        statisticsList.get(i).getW_sv_gms(),
                        statisticsList.get(i).getW_bp_saved(),
                        statisticsList.get(i).getW_bp_faced(),
                        statisticsList.get(i).getL_ace(),
                        statisticsList.get(i).getL_df(),
                        statisticsList.get(i).getL_svpt(),
                        statisticsList.get(i).getL_1st_in(),
                        statisticsList.get(i).getL_1st_won(),
                        statisticsList.get(i).getL_2nd_won(),
                        statisticsList.get(i).getL_sv_gms(),
                        statisticsList.get(i).getL_bp_saved(),
                        statisticsList.get(i).getL_bp_faced(),
                        statisticsList.get(i).getWinner_rank(),
                        statisticsList.get(i).getWinner_rank_points(),
                        statisticsList.get(i).getLoser_rank(),
                        statisticsList.get(i).getLoser_rank_points(),
                        statisticsList.get(i).getWinnerPlayer(),
                        statisticsList.get(i).getLoserPlayer(),
                        tourney
                );
                matchList.add(match);
            }
        }

        return matchList;
    }

    public List<Ranking> getDateRangeRanking(String date_start,String date_end) throws ParseException {
        List<Ranking> allRankings = (List<Ranking>) rankingRepository.findAll();
        List<Ranking> rangeRankings = new ArrayList<>();
        if(!allRankings.isEmpty()){
            for(int i = 0; i < allRankings.size(); i++){
                if( CommonHelper.compareTwoDate(date_start,date_end,allRankings.get(i).getRankingDate())){
                    rangeRankings.add(allRankings.get(i));
                }
            }
        }
        return rangeRankings;
    }

    public List<Match> getDateRangeMatch(String date_start,String date_end) throws ParseException {
        List<Statistics> allMatch = (List<Statistics>) statisticsRepository.findAll();
        List<Match> rangeMatch = new ArrayList<>();
        if(!allMatch.isEmpty()){
            for(int i = 0; i < allMatch.size(); i++){
                if( CommonHelper.compareTwoDate(date_start,date_end,allMatch.get(i).getTourney().getTourney_date())){
                    Tourney tourney = allMatch.get(i).getTourney();
                    Match match = new Match(
                            allMatch.get(i).getTourneyId(),
                            tourney.getTourneyName(),
                            tourney.getSurface(),
                            tourney.getDraw_size(),
                            tourney.getLevel(),
                            tourney.getTourney_date(),
                            allMatch.get(i).getMatchNum(),
                            allMatch.get(i).getWinner_id(),
                            allMatch.get(i).getWinner_seed(),
                            allMatch.get(i).getWinner_entry(),
                            allMatch.get(i).getWinnerName(),
                            allMatch.get(i).getWinner_hand(),
                            allMatch.get(i).getWinner_ht(),
                            allMatch.get(i).getWinner_ioc(),
                            allMatch.get(i).getWinner_age(),
                            allMatch.get(i).getLoser_id(),
                            allMatch.get(i).getLoser_seed(),
                            allMatch.get(i).getLoser_entry(),
                            allMatch.get(i).getLoserName(),
                            allMatch.get(i).getLoser_hand(),
                            allMatch.get(i).getLoser_ioc(),
                            allMatch.get(i).getLoser_ht(),
                            allMatch.get(i).getLoser_age(),
                            allMatch.get(i).getScore(),
                            allMatch.get(i).getBest_of(),
                            allMatch.get(i).getRound(),
                            allMatch.get(i).getMinutes(),
                            allMatch.get(i).getW_ace(),
                            allMatch.get(i).getW_df(),
                            allMatch.get(i).getW_svpt(),
                            allMatch.get(i).getW_1st_in(),
                            allMatch.get(i).getW_1st_won(),
                            allMatch.get(i).getW_2nd_won(),
                            allMatch.get(i).getW_sv_gms(),
                            allMatch.get(i).getW_bp_saved(),
                            allMatch.get(i).getW_bp_faced(),
                            allMatch.get(i).getL_ace(),
                            allMatch.get(i).getL_df(),
                            allMatch.get(i).getL_svpt(),
                            allMatch.get(i).getL_1st_in(),
                            allMatch.get(i).getL_1st_won(),
                            allMatch.get(i).getL_2nd_won(),
                            allMatch.get(i).getL_sv_gms(),
                            allMatch.get(i).getL_bp_saved(),
                            allMatch.get(i).getL_bp_faced(),
                            allMatch.get(i).getWinner_rank(),
                            allMatch.get(i).getWinner_rank_points(),
                            allMatch.get(i).getLoser_rank(),
                            allMatch.get(i).getLoser_rank_points(),
                            allMatch.get(i).getWinnerPlayer(),
                            allMatch.get(i).getLoserPlayer(),
                            tourney
                    );
                    rangeMatch.add(match);
                }
            }
        }
        return rangeMatch;
    }

    public List<Tourney> getDateRangeTourney(String date_start,String date_end) throws ParseException {
        List<Tourney> allTourney = (List<Tourney>) tourneyRepository.findAll();
        List<Tourney> rangeTourney = new ArrayList<>();
        if(!allTourney.isEmpty()){
            for(int i = 0; i < allTourney.size(); i++){
                if( CommonHelper.compareTwoDate(date_start,date_end,allTourney.get(i).getTourney_date())){
                    rangeTourney.add(allTourney.get(i));
                }
            }
        }
        return rangeTourney;
    }

    public List<Player> getDateBirthRangePlayer(String date_start,String date_end) throws ParseException {
        List<Player> allPlayer = (List<Player>) playerRepository.findAll();
        List<Player> rangePlayer = new ArrayList<>();
        if(!allPlayer.isEmpty()){
            for(int i = 0; i < allPlayer.size(); i++){
                if( CommonHelper.compareTwoDate(date_start,date_end,allPlayer.get(i).getBirth_date())){
                    rangePlayer.add(allPlayer.get(i));
                }
            }
        }
        return rangePlayer;
    }


}
