package com.tennisscorer.dto;

public class TourneyMatchDTO {

    public String winnerPlayer;
    public String loserPlayer;
    public String score;
    public Integer match_num;
    public MatchStatisticsDTO matchStatisticsDTO;

    public TourneyMatchDTO(String winnerPlayer, String loserPlayer, String score, Integer match_num, MatchStatisticsDTO matchStatisticsDTO, String match_date) {
        this.winnerPlayer = winnerPlayer;
        this.loserPlayer = loserPlayer;
        this.score = score;
        this.match_num = match_num;
        this.matchStatisticsDTO = matchStatisticsDTO;
        this.match_date = match_date;
    }

    public MatchStatisticsDTO getMatchStatistics() {
        return matchStatisticsDTO;
    }

    public void setMatchStatistics(MatchStatisticsDTO matchStatisticsDTO) {
        this.matchStatisticsDTO = matchStatisticsDTO;
    }
    public String getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(String winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public String getLoserPlayer() {
        return loserPlayer;
    }

    public void setLoserPlayer(String loserPlayer) {
        this.loserPlayer = loserPlayer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getMatch_num() {
        return match_num;
    }

    public void setMatch_num(Integer match_num) {
        this.match_num = match_num;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String match_date;



}
