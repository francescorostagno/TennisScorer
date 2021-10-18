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

    public List<TourneyDTO> getTourneyByName(String tourney_name){
        List<TourneyDTO> tourneysDTO = new ArrayList<>();
        List<Tourney> tourneys = tourneyRepository.findAllByTourneyName(tourney_name);
        if(tourneys != null){
            for(int i = 0; i < tourneys.size(); i++){
                TourneyDTO tourneyDTO = new TourneyDTO(
                        tourneys.get(i).getTourney_id(),
                        tourneys.get(i).getTourneyName(),
                        tourneys.get(i).getTourneyDate(),
                        tourneys.get(i).getDraw_size(),
                        tourneys.get(i).getLevel(),
                        tourneys.get(i).getSurface(),
                        tourneys.get(i).getWinnerName(),
                        tourneys.get(i).getLoserName(),
                        tourneys.get(i).getMatch_num()
                );
                tourneysDTO.add(tourneyDTO);
            }
        }
        return tourneysDTO;
    }

    public PlayerDTO getPlayerByName(String player_name){
        Player player = playerRepository.findByPlayerName(player_name);

        if(player != null){
            return new PlayerDTO(
                    player.getPlayerId(),
                    player.getPlayerName(),
                    player.getHand(),
                    player.getBirthDate(),
                    player.getCountry_code()
            );
        }else{
            return null;
        }

    }

    public MatchStatisticsDTO getMatchStatistics(String tourney_id, Integer match_num){
        Statistics tennisMatch = statisticsRepository.findByTourneyIdAndMatchNum(tourney_id,match_num);
        return new MatchStatisticsDTO(
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

    public List<RankingDTO> getPlayerRanking(String player_name) {
        Player player = playerRepository.findByPlayerName(player_name);
        List<RankingDTO> rankingsDTO = new ArrayList<>();
        if (player != null) {
            List<Ranking> ranking = rankingRepository.findAllByPlayerId(player.getPlayerId());
            if(!ranking.isEmpty()){
                for(int i = 0; i < ranking.size(); i++){
                   RankingDTO rankingDTO = new RankingDTO(
                            ranking.get(i).getRankingDate(),
                            ranking.get(i).getRank(),
                            ranking.get(i).getPlayer_id(),
                            ranking.get(i).getPoints()
                   );
                   rankingsDTO.add(rankingDTO);
                }
            }
        }
        return rankingsDTO;
    }

    public List<PlayerMatchDTO> getPlayerMatch(String player_name){
        List<Statistics> tennisMatchesWinner = statisticsRepository.findAllByWinnerName(player_name);
        List<PlayerMatchDTO> allPlayerMatchDTOS = new ArrayList<>();
        if(tennisMatchesWinner != null){
            for( int i = 0; i < tennisMatchesWinner.size(); i ++){
                PlayerMatchDTO playerMatchDTO = new PlayerMatchDTO(
                        tennisMatchesWinner.get(i).getTourney().getTourneyName(),
                        tennisMatchesWinner.get(i).getScore(),
                        tennisMatchesWinner.get(i).getLoserName(),
                        1
                );
                allPlayerMatchDTOS.add(playerMatchDTO);
            }
        }
        List<Statistics> tennisMatchesLoser = statisticsRepository.findAllByLoserName(player_name);
        if(tennisMatchesLoser != null){
            for(int i = 0; i < tennisMatchesLoser.size(); i ++){
                PlayerMatchDTO playerMatchDTO = new PlayerMatchDTO(
                        tennisMatchesLoser.get(i).getTourney().getTourneyName(),
                        tennisMatchesLoser.get(i).getScore(),
                        tennisMatchesLoser.get(i).getWinnerName(),
                        0
                );

                allPlayerMatchDTOS.add(playerMatchDTO);
            }
        }

        return allPlayerMatchDTOS;
    }

    public List<GoldenRegisterDTO> getTourneyGoldenRegister(String tourney_name){
            List<Tourney> tourneys = tourneyRepository.findAllByTourneyName(tourney_name);
            List<GoldenRegisterDTO> goldenRegisterDTOS = new ArrayList<>();
            if(!tourneys.isEmpty()){
                for(int i = 0; i < tourneys.size(); i++){
                    GoldenRegisterDTO goldenResister = new GoldenRegisterDTO(
                            tourney_name,
                            tourneys.get(i).getSurface(),
                            tourneys.get(i).getTourneyDate(),
                            tourneys.get(i).getWinnerName(),
                            tourneys.get(i).getLoserName(),
                            tourneys.get(i).getWinnerPlayer(),
                            tourneys.get(i).getLoserPlayer()
                    );
                    goldenRegisterDTOS.add(goldenResister);
                }
            }

            return goldenRegisterDTOS;
    }

    public List<String> autocompletePlayer(String term){
        return playerRepository.getPlayerTerm(term);
    }


    public List<TourneyDTO> getAllTourney(){
        List<TourneyDTO> tourneysDTO = new ArrayList<>();
        List<Tourney> tourneys = (List<Tourney>) tourneyRepository.findAll();
        for ( int i = 0; i < tourneys.size(); i++){
            TourneyDTO tourneyDTO = new TourneyDTO(
                    tourneys.get(i).getTourney_id(),
                    tourneys.get(i).getTourneyName(),
                    tourneys.get(i).getTourneyDate(),
                    tourneys.get(i).getDraw_size(),
                    tourneys.get(i).getLevel(),
                    tourneys.get(i).getSurface(),
                    tourneys.get(i).getWinnerName(),
                    tourneys.get(i).getLoserName(),
                    tourneys.get(i).getMatch_num()
            );
            tourneysDTO.add(tourneyDTO);
        }
        return tourneysDTO;
    }

    public List<TourneyDTO> getAllPlayerWinnerTourney(String playerName){
        List<Tourney> winnerTourneys = tourneyRepository.findAllByWinnerPlayer(playerRepository.findByPlayerName(playerName));
        List<TourneyDTO> tourneysDTO = new ArrayList<>();
        if( !winnerTourneys.isEmpty()){
            for (int i = 0; i < tourneysDTO.size(); i++){
                TourneyDTO tourneyDTO = new TourneyDTO(
                        tourneysDTO.get(i).getTourney_id(),
                        tourneysDTO.get(i).getTourneyName(),
                        tourneysDTO.get(i).getTourney_date(),
                        tourneysDTO.get(i).getDraw_size(),
                        tourneysDTO.get(i).getLevel(),
                        tourneysDTO.get(i).getSurface(),
                        tourneysDTO.get(i).getWinnerName(),
                        tourneysDTO.get(i).getLoserName(),
                        tourneysDTO.get(i).getMatch_num()
                );
                tourneysDTO.add(tourneyDTO);
            }
        }
        return tourneysDTO;
    }

    public List<TourneyMatchDTO> getAllTourneyMatch(String tourney_id){
        List<TourneyMatchDTO> tourneyMatchDTOS = new ArrayList<>();
        List<Statistics> tennisMatches = statisticsRepository.findAllByTourneyId(tourney_id);
        if(!tennisMatches.isEmpty()){
            for (int i = 0; i < tennisMatches.size(); i++){
                TourneyMatchDTO tourneyMatchDTO = new TourneyMatchDTO(
                        tennisMatches.get(i).getWinnerName(),
                        tennisMatches.get(i).getLoserName(),
                        tennisMatches.get(i).getScore(),
                        tennisMatches.get(i).getMatchNum(),
                        getMatchStatistics(tourney_id,tennisMatches.get(i).getMatchNum()),
                        tennisMatches.get(i).getTourney().getTourneyDate()
                );

                tourneyMatchDTOS.add(tourneyMatchDTO);
            }
        }
        return tourneyMatchDTOS;
    }

    public List<MatchDTO> getAllMatch(){
        List<Statistics> statisticsList = (List<Statistics>) statisticsRepository.findAll();
        List<MatchDTO> matchDTOList = new ArrayList<>();
        if( !statisticsList.isEmpty()){
            for(int i = 0; i < statisticsList.size(); i ++){
                Tourney tourney = statisticsList.get(i).getTourney();
                MatchDTO matchDTO = new MatchDTO(
                        statisticsList.get(i).getTourneyId(),
                        tourney.getTourneyName(),
                        tourney.getSurface(),
                        tourney.getDraw_size(),
                        tourney.getLevel(),
                        tourney.getTourneyDate(),
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
                        statisticsList.get(i).getLoser_rank_points()
                );
                matchDTOList.add(matchDTO);
            }
        }

        return matchDTOList;
    }

    public List<RankingDTO> getDateRangeRanking(String date_start,String date_end) throws ParseException {
        List<Ranking> allRankings = rankingRepository.findByRankingDateBetweenOrderByRankingDateDesc(date_start,date_end);
        List<RankingDTO> rangeRankingsDTO = new ArrayList<>();
        if(!allRankings.isEmpty()){
            for(int i = 0; i < allRankings.size(); i++){
                RankingDTO rankingDTO = new RankingDTO(
                        allRankings.get(i).getRankingDate(),
                        allRankings.get(i).getRank(),
                        allRankings.get(i).getPlayer_id(),
                        allRankings.get(i).getPoints()
                );
                rangeRankingsDTO.add(rankingDTO);
            }
        }
        return rangeRankingsDTO;
    }

    public List<MatchDTO> getDateRangeMatch(String date_start, String date_end) {
        List<Statistics> allMatch = (List<Statistics>) statisticsRepository.findAll();
        List<MatchDTO> rangeMatchDTOS = new ArrayList<>();
        if(!allMatch.isEmpty()){
            for(int i = 0; i < allMatch.size(); i++){
                if( CommonHelper.compareTwoDate(date_start,date_end,allMatch.get(i).getTourney().getTourneyDate())){
                    Tourney tourney = allMatch.get(i).getTourney();
                    MatchDTO matchDTO = new MatchDTO(
                            allMatch.get(i).getTourneyId(),
                            tourney.getTourneyName(),
                            tourney.getSurface(),
                            tourney.getDraw_size(),
                            tourney.getLevel(),
                            tourney.getTourneyDate(),
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
                            allMatch.get(i).getLoser_rank_points()
                    );
                    rangeMatchDTOS.add(matchDTO);
                }
            }
        }
        return rangeMatchDTOS;
    }

    public List<TourneyDTO> getDateRangeTourney(String date_start,String date_end) throws ParseException {
        List<Tourney> allTourney =  tourneyRepository.findByTourneyDateBetweenOrderByTourneyDateDesc(date_start,date_end);

        List<TourneyDTO> rangeTourneysDTO = new ArrayList<>();

        if(!allTourney.isEmpty()){
            for(int i = 0; i < allTourney.size(); i++){
                TourneyDTO tourneyDTO = new TourneyDTO(
                        allTourney.get(i).getTourney_id(),
                        allTourney.get(i).getTourneyName(),
                        allTourney.get(i).getTourneyDate(),
                        allTourney.get(i).getDraw_size(),
                        allTourney.get(i).getLevel(),
                        allTourney.get(i).getSurface(),
                        allTourney.get(i).getWinnerName(),
                        allTourney.get(i).getLoserName(),
                        allTourney.get(i).getMatch_num()
                );

                rangeTourneysDTO.add(tourneyDTO);
            }
        }
        return rangeTourneysDTO;
    }

    public List<PlayerDTO> getDateBirthRangePlayer(String date_start,String date_end) throws ParseException {
        List<Player> allPlayer =  playerRepository.findByBirthDateBetweenOrderByBirthDateDesc(date_start,date_end);

        List<PlayerDTO> rangePlayersDTO = new ArrayList<>();
        if(!allPlayer.isEmpty()){
            for(int i = 0; i < allPlayer.size(); i++){
                PlayerDTO playerDTO = new PlayerDTO(
                        allPlayer.get(i).getPlayerId(),
                        allPlayer.get(i).getPlayerName(),
                        allPlayer.get(i).getHand(),
                        allPlayer.get(i).getBirthDate(),
                        allPlayer.get(i).getCountry_code()
                );
                rangePlayersDTO.add(playerDTO);
            }

        }
        return rangePlayersDTO;
    }

    public TourneyDTO getTourneyById(String tourney_id){
        Tourney tourney = tourneyRepository.findTourneyByTourneyId(tourney_id);
        if(tourney != null){
            return new TourneyDTO(
                    tourney.getTourney_id(),
                    tourney.getTourneyName(),
                    tourney.getTourneyDate(),
                    tourney.getDraw_size(),
                    tourney.getLevel(),
                    tourney.getSurface(),
                    tourney.getWinnerName(),
                    tourney.getLoserName(),
                    tourney.getMatch_num()
            );
        }else {
            return null;
        }

    }
}
