package com.tennisscorer.service;


import com.tennisscorer.helper.CSVHelper;
import com.tennisscorer.model.Player;
import com.tennisscorer.model.Ranking;
import com.tennisscorer.model.Statistics;
import com.tennisscorer.model.Tourney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Configurable
@Service
public class CSVService {

    @Autowired
    com.tennisscorer.repository.PlayerRepository playerRepository;
    @Autowired
    com.tennisscorer.repository.TourneyRepository tourneyRepository;
    @Autowired
    com.tennisscorer.repository.RankingRepository rankingRepository;
    @Autowired
    com.tennisscorer.repository.StatisticsRepository statisticsRepository;


    public void saveStatistics(MultipartFile file){
        try{
            List<Statistics> statisticsList = CSVHelper.csvToStatistics(file.getInputStream());
            for (int i = 0; i < statisticsList.size(); i++){
                if (!statisticsRepository.existsStatisticsByTourneyIdAndMatchNum(statisticsList.get(i).getTourneyId(), statisticsList.get(i).getMatchNum())) {
                    statisticsRepository.save(statisticsList.get(i));
                }
            }

            List<Tourney> tourneys = CSVHelper.csvToTourney(file.getInputStream());

            for(int i = 0; i < tourneys.size(); i ++){
                if(!tourneyRepository.existsTourneyByTourneyId(tourneys.get(i).getTourney_id()) &&
                        tourneys.get(i).getDraw_size() == (tourneys.get(i).getMatch_num() + 1)  ){
                    tourneyRepository.save(tourneys.get(i));
                }
            }

        }catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void savePlayers(MultipartFile file){
        try {

            List<Player> players = CSVHelper.csvToPlayer(file.getInputStream());
            for (int i = 0; i < players.size() ; i++ ) {
                if(!playerRepository.existsPlayerByPlayerName(players.get(i).getPlayerName())){
                    playerRepository.save(players.get(i));
                }
            }

        }catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveRanking(MultipartFile file){
        try {

            List<Ranking> rankings = CSVHelper.csvToRanking(file.getInputStream());
            for (int i = 0; i < rankings.size() ; i++ ) {
                if(!rankingRepository.existsByPlayerIdAndRankingDate(rankings.get(i).getPlayer_id(),rankings.get(i).getRankingDate())
                        && rankings.get(i).getRank() <= 50 ){
                    rankingRepository.save(rankings.get(i));
                }
            }

        }catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(){
        List<Statistics> tennisMatches = (List<Statistics>) statisticsRepository.findAll();
        return CSVHelper.tennisMatchToCsv(tennisMatches);
    }


}
