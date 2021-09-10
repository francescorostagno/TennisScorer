package com.tennisscorer.dto;


import com.tennisscorer.model.Player;
import com.tennisscorer.model.Tourney;

public class Match {
    private String tourneyId;

    private String tourney_name;

    private String surface;

    private long draw_size;

    private String tourney_level;

    private String tourney_date;

    private int matchNum;

    private long winner_id;

    private int winner_seed;

    private String winner_entry;

    private String winnerName;

    private String winner_hand;

    private int winner_ht;

    private String winner_ioc;

    private double winner_age;

    private long loser_id;

    private int loser_seed;

    private String loser_entry;

    private String loserName;

    private String loser_hand;

    private String loser_ioc;

    private int loser_ht;

    private double loser_age;

    private String score;

    private int best_of;

    private String round;

    private int minutes;

    private int w_ace;

    private int w_df;

    private int w_svpt;

    private int w_1st_in;

    private int w_1st_won;

    private int w_2nd_won;

    private int w_sv_gms;

    private int w_bp_saved;

    private int w_bp_faced;

    private int l_ace;

    private int l_df;

    private int l_svpt;

    private int l_1st_in;

    private int l_1st_won;

    private int l_2nd_won;

    private int l_sv_gms;

    private int l_bp_saved;

    private int l_bp_faced;

    private int winner_rank;

    private int winner_rank_points;

    private int loser_rank;

    private int loser_rank_points;


    public Match() {

    }

    public Match(String tourneyId, String tourney_name, String surface, long draw_size, String tourney_level, String tourney_date, int matchNum, long winner_id, int winner_seed, String winner_entry, String winnerName, String winner_hand, int winner_ht, String winner_ioc, double winner_age, long loser_id, int loser_seed, String loser_entry, String loserName, String loser_hand, String loser_ioc, int loser_ht, double loser_age, String score, int best_of, String round, int minutes, int w_ace, int w_df, int w_svpt, int w_1st_in, int w_1st_won, int w_2nd_won, int w_sv_gms, int w_bp_saved, int w_bp_faced, int l_ace, int l_df, int l_svpt, int l_1st_in, int l_1st_won, int l_2nd_won, int l_sv_gms, int l_bp_saved, int l_bp_faced, int winner_rank, int winner_rank_points, int loser_rank, int loser_rank_points) {
        this.tourneyId = tourneyId;
        this.tourney_name = tourney_name;
        this.surface = surface;
        this.draw_size = draw_size;
        this.tourney_level = tourney_level;
        this.tourney_date = tourney_date;
        this.matchNum = matchNum;
        this.winner_id = winner_id;
        this.winner_seed = winner_seed;
        this.winner_entry = winner_entry;
        this.winnerName = winnerName;
        this.winner_hand = winner_hand;
        this.winner_ht = winner_ht;
        this.winner_ioc = winner_ioc;
        this.winner_age = winner_age;
        this.loser_id = loser_id;
        this.loser_seed = loser_seed;
        this.loser_entry = loser_entry;
        this.loserName = loserName;
        this.loser_hand = loser_hand;
        this.loser_ioc = loser_ioc;
        this.loser_ht = loser_ht;
        this.loser_age = loser_age;
        this.score = score;
        this.best_of = best_of;
        this.round = round;
        this.minutes = minutes;
        this.w_ace = w_ace;
        this.w_df = w_df;
        this.w_svpt = w_svpt;
        this.w_1st_in = w_1st_in;
        this.w_1st_won = w_1st_won;
        this.w_2nd_won = w_2nd_won;
        this.w_sv_gms = w_sv_gms;
        this.w_bp_saved = w_bp_saved;
        this.w_bp_faced = w_bp_faced;
        this.l_ace = l_ace;
        this.l_df = l_df;
        this.l_svpt = l_svpt;
        this.l_1st_in = l_1st_in;
        this.l_1st_won = l_1st_won;
        this.l_2nd_won = l_2nd_won;
        this.l_sv_gms = l_sv_gms;
        this.l_bp_saved = l_bp_saved;
        this.l_bp_faced = l_bp_faced;
        this.winner_rank = winner_rank;
        this.winner_rank_points = winner_rank_points;
        this.loser_rank = loser_rank;
        this.loser_rank_points = loser_rank_points;
    }

    public String getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(String tourneyId) {
        this.tourneyId = tourneyId;
    }

    public String getTourney_name() {
        return tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public long getDraw_size() {
        return draw_size;
    }

    public void setDraw_size(long draw_size) {
        this.draw_size = draw_size;
    }

    public String getTourney_level() {
        return tourney_level;
    }

    public void setTourney_level(String tourney_level) {
        this.tourney_level = tourney_level;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public long getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(long winner_id) {
        this.winner_id = winner_id;
    }

    public int getWinner_seed() {
        return winner_seed;
    }

    public void setWinner_seed(int winner_seed) {
        this.winner_seed = winner_seed;
    }

    public String getWinner_entry() {
        return winner_entry;
    }

    public void setWinner_entry(String winner_entry) {
        this.winner_entry = winner_entry;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinner_hand() {
        return winner_hand;
    }

    public void setWinner_hand(String winner_hand) {
        this.winner_hand = winner_hand;
    }

    public int getWinner_ht() {
        return winner_ht;
    }

    public void setWinner_ht(int winner_ht) {
        this.winner_ht = winner_ht;
    }

    public String getWinner_ioc() {
        return winner_ioc;
    }

    public void setWinner_ioc(String winner_ioc) {
        this.winner_ioc = winner_ioc;
    }

    public double getWinner_age() {
        return winner_age;
    }

    public void setWinner_age(double winner_age) {
        this.winner_age = winner_age;
    }

    public long getLoser_id() {
        return loser_id;
    }

    public void setLoser_id(long loser_id) {
        this.loser_id = loser_id;
    }

    public int getLoser_seed() {
        return loser_seed;
    }

    public void setLoser_seed(int loser_seed) {
        this.loser_seed = loser_seed;
    }

    public String getLoser_entry() {
        return loser_entry;
    }

    public void setLoser_entry(String loser_entry) {
        this.loser_entry = loser_entry;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public String getLoser_hand() {
        return loser_hand;
    }

    public void setLoser_hand(String loser_hand) {
        this.loser_hand = loser_hand;
    }

    public String getLoser_ioc() {
        return loser_ioc;
    }

    public void setLoser_ioc(String loser_ioc) {
        this.loser_ioc = loser_ioc;
    }

    public int getLoser_ht() {
        return loser_ht;
    }

    public void setLoser_ht(int loser_ht) {
        this.loser_ht = loser_ht;
    }

    public double getLoser_age() {
        return loser_age;
    }

    public void setLoser_age(double loser_age) {
        this.loser_age = loser_age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getBest_of() {
        return best_of;
    }

    public void setBest_of(int best_of) {
        this.best_of = best_of;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getW_ace() {
        return w_ace;
    }

    public void setW_ace(int w_ace) {
        this.w_ace = w_ace;
    }

    public int getW_df() {
        return w_df;
    }

    public void setW_df(int w_df) {
        this.w_df = w_df;
    }

    public int getW_svpt() {
        return w_svpt;
    }

    public void setW_svpt(int w_svpt) {
        this.w_svpt = w_svpt;
    }

    public int getW_1st_in() {
        return w_1st_in;
    }

    public void setW_1st_in(int w_1st_in) {
        this.w_1st_in = w_1st_in;
    }

    public int getW_1st_won() {
        return w_1st_won;
    }

    public void setW_1st_won(int w_1st_won) {
        this.w_1st_won = w_1st_won;
    }

    public int getW_2nd_won() {
        return w_2nd_won;
    }

    public void setW_2nd_won(int w_2nd_won) {
        this.w_2nd_won = w_2nd_won;
    }

    public int getW_sv_gms() {
        return w_sv_gms;
    }

    public void setW_sv_gms(int w_sv_gms) {
        this.w_sv_gms = w_sv_gms;
    }

    public int getW_bp_saved() {
        return w_bp_saved;
    }

    public void setW_bp_saved(int w_bp_saved) {
        this.w_bp_saved = w_bp_saved;
    }

    public int getW_bp_faced() {
        return w_bp_faced;
    }

    public void setW_bp_faced(int w_bp_faced) {
        this.w_bp_faced = w_bp_faced;
    }

    public int getL_ace() {
        return l_ace;
    }

    public void setL_ace(int l_ace) {
        this.l_ace = l_ace;
    }

    public int getL_df() {
        return l_df;
    }

    public void setL_df(int l_df) {
        this.l_df = l_df;
    }

    public int getL_svpt() {
        return l_svpt;
    }

    public void setL_svpt(int l_svpt) {
        this.l_svpt = l_svpt;
    }

    public int getL_1st_in() {
        return l_1st_in;
    }

    public void setL_1st_in(int l_1st_in) {
        this.l_1st_in = l_1st_in;
    }

    public int getL_1st_won() {
        return l_1st_won;
    }

    public void setL_1st_won(int l_1st_won) {
        this.l_1st_won = l_1st_won;
    }

    public int getL_2nd_won() {
        return l_2nd_won;
    }

    public void setL_2nd_won(int l_2nd_won) {
        this.l_2nd_won = l_2nd_won;
    }

    public int getL_sv_gms() {
        return l_sv_gms;
    }

    public void setL_sv_gms(int l_sv_gms) {
        this.l_sv_gms = l_sv_gms;
    }

    public int getL_bp_saved() {
        return l_bp_saved;
    }

    public void setL_bp_saved(int l_bp_saved) {
        this.l_bp_saved = l_bp_saved;
    }

    public int getL_bp_faced() {
        return l_bp_faced;
    }

    public void setL_bp_faced(int l_bp_faced) {
        this.l_bp_faced = l_bp_faced;
    }

    public int getWinner_rank() {
        return winner_rank;
    }

    public void setWinner_rank(int winner_rank) {
        this.winner_rank = winner_rank;
    }

    public int getWinner_rank_points() {
        return winner_rank_points;
    }

    public void setWinner_rank_points(int winner_rank_points) {
        this.winner_rank_points = winner_rank_points;
    }

    public int getLoser_rank() {
        return loser_rank;
    }

    public void setLoser_rank(int loser_rank) {
        this.loser_rank = loser_rank;
    }

    public int getLoser_rank_points() {
        return loser_rank_points;
    }

    public void setLoser_rank_points(int loser_rank_points) {
        this.loser_rank_points = loser_rank_points;
    }
}
