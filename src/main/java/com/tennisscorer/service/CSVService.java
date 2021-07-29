package com.tennisscorer.service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.tennisscorer.model.Player;
import com.tennisscorer.model.Ranking;
import com.tennisscorer.model.Tourney;
import com.tennisscorer.repository.TennisRepository;
import com.tennisscorer.helper.CSVHelper;
import com.tennisscorer.model.TennisMatch;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Configurable
@Service
public class CSVService {

    @Autowired
    com.tennisscorer.repository.TennisRepository repository;
    @Autowired
    com.tennisscorer.repository.PlayerRepository playerRepository;
    @Autowired
    com.tennisscorer.repository.TourneyRepository tourneyRepository;
    @Autowired
    com.tennisscorer.repository.RankingRepository rankingRepository;


    public void save(MultipartFile file){
        try{
            List<TennisMatch> tennisMatches = CSVHelper.csvToTennisMatch(file.getInputStream());
            for(int i = 0; i < tennisMatches.size(); i ++){
                if(!repository.existsTennisMatchByTourneyIdAndMatchNum(tennisMatches.get(i).getTourneyId(),tennisMatches.get(i).getMatch_num())){
                    repository.save(tennisMatches.get(i));
                }
            }

            List<Tourney> tourneys = CSVHelper.csvToTourney(file.getInputStream());

            for(int i = 0; i < tourneys.size(); i ++){
                if(!tourneyRepository.existsTourneyByTourneyId(tourneys.get(i).getTourney_id()) &&
                    tourneys.get(i).getDraw_size() == (tourneys.get(i).getMatch_num() + 1)  ){
                    tourneyRepository.save(tourneys.get(i));
                }
            }

        }catch (IOException e) {
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
        List<TennisMatch> tennisMatches = (List<TennisMatch>) repository.findAll();
        return CSVHelper.tennisMatchToCsv(tennisMatches);
    }

    public List<TennisMatch> getAllTennisMatch(){
        return (List<TennisMatch>) repository.findAll();
    }

}
