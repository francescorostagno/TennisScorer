package com.tennisscorer.controller;

import com.tennisscorer.dto.*;
import com.tennisscorer.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    CommonService commonService;

    @GetMapping("/all_tourney")
    public ResponseEntity<List<TourneyDTO>> getTourneyTennisMatch(){
        List<TourneyDTO> tourneys = commonService.getAllTourney();
        if(tourneys.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }


    @GetMapping("/match_statistics")
    public ResponseEntity<MatchStatisticsDTO> getMatchStatistics(@RequestParam("tourney_id") String tourney_id, @RequestParam("match_num") Integer match_num){
        if(("").equals(tourney_id) || match_num.equals(0)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MatchStatisticsDTO matchStatisticsDTO = commonService.getMatchStatistics(tourney_id,match_num );
        if(matchStatisticsDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(matchStatisticsDTO, HttpStatus.OK);
    }

    @GetMapping("/match")
    public ResponseEntity<List<MatchDTO>> getAllMatch(){
        List<MatchDTO> tennisMatchDTOS = commonService.getAllMatch();
        if(tennisMatchDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tennisMatchDTOS, HttpStatus.OK);
    }

    @GetMapping("/player_ranking")
    public ResponseEntity<List<RankingDTO>> getPlayerRanking(@RequestParam("player_name") String player_name){
        if(("").equals(player_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<RankingDTO> rankings = commonService.getPlayerRanking(player_name);
        if(rankings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @GetMapping("/player_match")
    public ResponseEntity<List<PlayerMatchDTO>> getPlayerMatch(@RequestParam("player_name") String player_name){
        if(("").equals(player_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<PlayerMatchDTO> playerMatchDTOS = commonService.getPlayerMatch(player_name);
        if(playerMatchDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerMatchDTOS, HttpStatus.OK);
    }

    @GetMapping("/golden_register")
    public ResponseEntity<List<GoldenRegisterDTO>> getGoldenRegister(@RequestParam("tourney_name") String tourney_name){
        if(("").equals(tourney_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<GoldenRegisterDTO> goldenRegisterDTOS = commonService.getTourneyGoldenRegister(tourney_name);
        if(goldenRegisterDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(goldenRegisterDTOS, HttpStatus.OK);
    }

    @GetMapping("/tourney")
    public ResponseEntity<List<TourneyDTO>> getTourneyByName(@RequestParam("tourney_name") String tourney_name){
        if(("").equals(tourney_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<TourneyDTO> tourneys = commonService.getTourneyByName(tourney_name);
        if(tourneys.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }



    @GetMapping("/player")
    public ResponseEntity<PlayerDTO> getPlayerByName(@RequestParam("player_name") String player_name){
        if(("").equals(player_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PlayerDTO player = commonService.getPlayerByName(player_name);
        if(player == null ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @GetMapping("/tourney_id")
    public ResponseEntity<TourneyDTO> getTourneyByID(@RequestParam("tourney_id") String tourney_id){
        if(("").equals(tourney_id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TourneyDTO tourneyDTO = commonService.getTourneyById(tourney_id);
        if(tourneyDTO == null ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneyDTO, HttpStatus.OK);
    }

    @GetMapping("/player_winner_tourneys")
    public ResponseEntity<List<TourneyDTO>> getAllPlayerWinnerTourney(@RequestParam("player_name") String player_name){
        if(("").equals(player_name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<TourneyDTO> tourneys = commonService.getAllPlayerWinnerTourney(player_name);
        if(tourneys.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }

    @GetMapping("/tourney_match")
    public ResponseEntity<List<TourneyMatchDTO>> getAllTourneyMatch(@RequestParam("tourney_id") String tourney_id){
        if(("").equals(tourney_id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<TourneyMatchDTO> tourneyMatchDTOS = commonService.getAllTourneyMatch(tourney_id);
        if(tourneyMatchDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneyMatchDTOS,HttpStatus.OK);
    }

    @GetMapping("/autocomplete_player")
    public ResponseEntity<List<String>> autocompletePlayer(@RequestParam("term") String term){
        List<String> terms = commonService.autocompletePlayer(term);
        return new ResponseEntity<>(terms, HttpStatus.OK);
    }

    @GetMapping("/get_date_range_ranking")
    public ResponseEntity<List<RankingDTO>> getDateRangeRanking(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end) throws ParseException {
        if(("").equals(date_start) || ("").equals(date_end)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<RankingDTO> rankings = commonService.getDateRangeRanking(date_start,date_end);
        if(rankings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @GetMapping("/get_date_range_match")
    public ResponseEntity<List<MatchDTO>> getDateRangeMatch(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end){
        if(("").equals(date_start) || ("").equals(date_end)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<MatchDTO> matchDTOS = commonService.getDateRangeMatch(date_start,date_end);
        if(matchDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(matchDTOS, HttpStatus.OK);
    }

    @GetMapping("/get_date_range_tourney")
    public ResponseEntity<List<TourneyDTO>> getDateRangeTourney(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end) throws ParseException {
        if(("").equals(date_start) || ("").equals(date_end)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<TourneyDTO> tourneys = commonService.getDateRangeTourney(date_start,date_end);
        if(tourneys.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }

    @GetMapping("/get_date_birth_range_player")
    public ResponseEntity<List<PlayerDTO>> getDateBirthRangePlayer(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end) throws ParseException {
        if(("").equals(date_start) || ("").equals(date_end)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<PlayerDTO> players = commonService.getDateBirthRangePlayer(date_start,date_end);
        if(players.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }



}
