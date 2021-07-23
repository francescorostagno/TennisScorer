package com.tennisscorer.controller;

import com.tennisscorer.model.MatchStatistics;
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

    @GetMapping("/all_tourney")
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


    @GetMapping("/tourney_match")
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

    @GetMapping("/match_statistics")
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

    @GetMapping("/player_ranking")
    public ResponseEntity<List<Ranking>> getPlayerRanking(@RequestParam("player_id") String player_name){
        try {
            List<Ranking> rankings = commonService.getPlayerRanking(player_name);
            if(rankings == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rankings, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
