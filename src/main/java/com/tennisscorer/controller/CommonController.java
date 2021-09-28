package com.tennisscorer.controller;

import com.tennisscorer.dto.*;
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
    CommonService commonService;

    @GetMapping("/all_tourney")
    public ResponseEntity<List<TourneyDTO>> getTourneyTennisMatch(){
        try {
            List<TourneyDTO> tourneys = commonService.getAllTourney();
            if(tourneys.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tourneys, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/match_statistics")
    public ResponseEntity<MatchStatisticsDTO> getMatchStatistics(@RequestParam("tourney_id") String tourney_id, @RequestParam("match_num") Integer match_num){
        try {
            if(tourney_id.equals("") || match_num.equals(0) ){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                MatchStatisticsDTO matchStatisticsDTO = commonService.getMatchStatistics(tourney_id,match_num );
                if(matchStatisticsDTO == null){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(matchStatisticsDTO, HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/match")
    public ResponseEntity<List<MatchDTO>> getAllMatch(){
        try {
            List<MatchDTO> tennisMatchDTOS = commonService.getAllMatch();
            if(tennisMatchDTOS.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tennisMatchDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/player_ranking")
    public ResponseEntity<List<RankingDTO>> getPlayerRanking(@RequestParam("player_name") String player_name){
        try {
            if(player_name.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                List<RankingDTO> rankings = commonService.getPlayerRanking(player_name);
                if(rankings.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(rankings, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/player_match")
    public ResponseEntity<List<PlayerMatchDTO>> getPlayerMatch(@RequestParam("player_name") String player_name){
        try {
            if(player_name.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<PlayerMatchDTO> playerMatchDTOS = commonService.getPlayerMatch(player_name);
                if(playerMatchDTOS.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(playerMatchDTOS, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/golden_register")
    public ResponseEntity<List<GoldenRegisterDTO>> getGoldenRegister(@RequestParam("tourney_name") String tourney_name){
        try {
            if( tourney_name.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                List<GoldenRegisterDTO> goldenRegisterDTOS = commonService.getTourneyGoldenRegister(tourney_name);
                if(goldenRegisterDTOS.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(goldenRegisterDTOS, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tourney")
    public ResponseEntity<List<TourneyDTO>> getTourneyByName(@RequestParam("tourney_name") String tourney_name){
        try {
            if(tourney_name.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<TourneyDTO> tourneys = commonService.getTourneyByName(tourney_name);
                if(tourneys.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tourneys, HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/player")
    public ResponseEntity<PlayerDTO> getPlayerByName(@RequestParam("player_name") String player_name){
        try {
            if(player_name.equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                PlayerDTO player = commonService.getPlayerByName(player_name);
                if(player == null ){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(player, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/player_winner_tourneys")
    public ResponseEntity<List<TourneyDTO>> getAllPlayerWinnerTourney(@RequestParam("player_name") String player_name){
        try{
            if(player_name.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                List<TourneyDTO> tourneys = commonService.getAllPlayerWinnerTourney(player_name);
                if(tourneys.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tourneys, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tourney_match")
    public ResponseEntity<List<TourneyMatchDTO>> getAllTourneyMatch(@RequestParam("tourney_id") String tourney_id){
        try {
            if(tourney_id.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                List<TourneyMatchDTO> tourneyMatchDTOS = commonService.getAllTourneyMatch(tourney_id);
                if(tourneyMatchDTOS.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tourneyMatchDTOS,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/autocomplete_player")
    public ResponseEntity<List<String>> autocompletePlayer(@RequestParam("term") String term){
        try{
            List<String> terms = commonService.autocompletePlayer(term);
            return new ResponseEntity<>(terms, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_date_range_ranking")
    public ResponseEntity<List<RankingDTO>> getDateRangeRanking(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end){
        try {
            if(date_start.equals("") || date_end.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<RankingDTO> rankings = commonService.getDateRangeRanking(date_start,date_end);
                if(rankings.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(rankings, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_date_range_match")
    public ResponseEntity<List<MatchDTO>> getDateRangeMatch(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end){
        try {
            if(date_start.equals("") || date_end.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<MatchDTO> matchDTOS = commonService.getDateRangeMatch(date_start,date_end);
                if(matchDTOS.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(matchDTOS, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_date_range_tourney")
    public ResponseEntity<List<TourneyDTO>> getDateRangeTourney(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end){
        try {
            if(date_start.equals("") || date_end.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<TourneyDTO> tourneys = commonService.getDateRangeTourney(date_start,date_end);
                if(tourneys.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tourneys, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_date_birth_range_player")
    public ResponseEntity<List<PlayerDTO>> getDateBirthRangePlayer(@RequestParam("date_start") String date_start, @RequestParam("date_end") String date_end){
        try {
            if(date_start.equals("") || date_end.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                List<PlayerDTO> players = commonService.getDateBirthRangePlayer(date_start,date_end);
                if(players.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(players, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
