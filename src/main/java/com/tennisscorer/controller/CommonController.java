package com.tennisscorer.controller;

import com.tennisscorer.dto.GoldenRegister;
import com.tennisscorer.dto.MatchStatistics;
import com.tennisscorer.dto.PlayerMatch;
import com.tennisscorer.model.Player;
import com.tennisscorer.model.Ranking;
import com.tennisscorer.model.TennisMatch;
import com.tennisscorer.model.Tourney;
import com.tennisscorer.repository.TourneyRepository;
import com.tennisscorer.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    TourneyRepository tourneyRepository;

    @Autowired
    CommonService commonService;

    @PostMapping("/all_tourney")
    public ResponseEntity<List<Tourney>> getTourneyTennisMatch(){
        try {
            List<Tourney> tourneys = commonService.getAllTourney();
            if(tourneys.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tourneys, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/tourney_match")
    public ResponseEntity<List<TennisMatch>> getTourneyTennisMatch(@RequestParam("tourney_id") String tourney_id){
        try {
            List<TennisMatch> tennisMatches = commonService.getTourneyTennisMatch(tourney_id);
            if(tennisMatches.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tennisMatches, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/match_statistics")
    public ResponseEntity<MatchStatistics> getMatchStatistics(@RequestParam("tourney_id") String tourney_id, @RequestParam("match_num") Integer match_num){
        try {
            MatchStatistics matchStatistics = commonService.getMatchStatistics(tourney_id,match_num );
            if(matchStatistics == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matchStatistics, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/player_ranking")
    public ResponseEntity<List<Ranking>> getPlayerRanking(@RequestParam("player_name") String player_name){
        try {
            List<Ranking> rankings = commonService.getPlayerRanking(player_name);
            if(rankings.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rankings, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/player_match")
    public ResponseEntity<List<PlayerMatch>> getPlayerMatch(@RequestParam("player_name") String player_name){
        try {
            List<PlayerMatch> playerMatches = commonService.getPlayerMatch(player_name);
            if(playerMatches.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(playerMatches, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/golden_register")
    public ResponseEntity<List<GoldenRegister>> getGoldenRegister(@RequestParam("tourney_name") String tourney_name){
        try {
            List<GoldenRegister> goldenRegisters = commonService.getTourneyGoldenRegister(tourney_name);
            if(goldenRegisters.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(goldenRegisters, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tourney")
    public ResponseEntity<List<Tourney>> getTourneyByName(@RequestParam("tourney_name") String tourney_name){
        try {
            List<Tourney> tourneys = commonService.getTourneyByName(tourney_name);
            if(tourneys.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tourneys, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/player")
    public ResponseEntity<Player> getPlayerByName(@RequestParam("player_name") String player_name){
        try {
            Player player = commonService.getPlayerByName(player_name);
            if(player_name == null ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(player, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/autocomplete_player")
    public ResponseEntity<List<String>> autocompletePlayer(@RequestParam("term") String term){
        try{
            List<String> terms = commonService.autocompletePlayer(term);
            return new ResponseEntity<>(terms, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
