package com.tennisscorer.service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.tennisscorer.model.Player;
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


    public void save(MultipartFile file){
        try{
            List<TennisMatch> tennisMatches = CSVHelper.csvToTennisMatch(file.getInputStream());
            for(int i = 0; i < tennisMatches.size(); i ++){
                if(!repository.existsTennisMatchByTourneyIdAndMatchNum(tennisMatches.get(i).getTourneyId(),tennisMatches.get(i).getMatch_num())){
                    repository.save(tennisMatches.get(i));
                }
            }
            List<Player> players = CSVHelper.csvToPlayer(file.getInputStream());
            List<Tourney> tourneys = CSVHelper.csvToTourney(file.getInputStream());

            for (int i = 0; i < players.size() ; i++ ) {
                if(!playerRepository.existsPlayerByPlayerName(players.get(i).getPlayer_name())){
                    playerRepository.save(players.get(i));
                }
            }

            for(int i = 0; i < tourneys.size(); i ++){
                if(!tourneyRepository.existsTourneyByTourneyId(tourneys.get(i).getTourney_id())){
                    tourneyRepository.save(tourneys.get(i));
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
