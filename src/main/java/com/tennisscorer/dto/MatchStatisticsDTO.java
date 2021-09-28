package com.tennisscorer.dto;

public class MatchStatisticsDTO {

    public Integer w_ace;
    public Integer w_df;
    public Integer w_svpt;
    public Integer w_1st_in;
    public Integer w_1st_won;
    public Integer w_2nd_won;
    public Integer w_sv_gms;
    public Integer w_bp_saved;
    public Integer w_bp_faced;
    public Integer l_ace;
    public Integer l_df;

    public Integer getW_ace() {
        return w_ace;
    }

    public MatchStatisticsDTO(Integer w_ace, Integer w_df, Integer w_svpt, Integer w_1st_in, Integer w_1st_won, Integer w_2nd_won, Integer w_sv_gms, Integer w_bp_saved, Integer w_bp_faced, Integer l_ace, Integer l_df, Integer l_svpt, Integer l_1st_in, Integer l_1st_won, Integer l_2nd_won, Integer l_sv_gms, Integer l_bp_saved, Integer l_bp_faced, Integer winner_rank, Integer winner_rank_points, Integer loser_rank, Integer loser_rank_points) {
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

    public void setW_ace(Integer w_ace) {
        this.w_ace = w_ace;
    }

    public Integer getW_df() {
        return w_df;
    }

    public void setW_df(Integer w_df) {
        this.w_df = w_df;
    }

    public Integer getW_svpt() {
        return w_svpt;
    }

    public void setW_svpt(Integer w_svpt) {
        this.w_svpt = w_svpt;
    }

    public Integer getW_1st_in() {
        return w_1st_in;
    }

    public void setW_1st_in(Integer w_1st_in) {
        this.w_1st_in = w_1st_in;
    }

    public Integer getW_1st_won() {
        return w_1st_won;
    }

    public void setW_1st_won(Integer w_1st_won) {
        this.w_1st_won = w_1st_won;
    }

    public Integer getW_2nd_won() {
        return w_2nd_won;
    }

    public void setW_2nd_won(Integer w_2nd_won) {
        this.w_2nd_won = w_2nd_won;
    }

    public Integer getW_sv_gms() {
        return w_sv_gms;
    }

    public void setW_sv_gms(Integer w_sv_gms) {
        this.w_sv_gms = w_sv_gms;
    }

    public Integer getW_bp_saved() {
        return w_bp_saved;
    }

    public void setW_bp_saved(Integer w_bp_saved) {
        this.w_bp_saved = w_bp_saved;
    }

    public Integer getW_bp_faced() {
        return w_bp_faced;
    }

    public void setW_bp_faced(Integer w_bp_faced) {
        this.w_bp_faced = w_bp_faced;
    }

    public Integer getL_ace() {
        return l_ace;
    }

    public void setL_ace(Integer l_ace) {
        this.l_ace = l_ace;
    }

    public Integer getL_df() {
        return l_df;
    }

    public void setL_df(Integer l_df) {
        this.l_df = l_df;
    }

    public Integer getL_svpt() {
        return l_svpt;
    }

    public void setL_svpt(Integer l_svpt) {
        this.l_svpt = l_svpt;
    }

    public Integer getL_1st_in() {
        return l_1st_in;
    }

    public void setL_1st_in(Integer l_1st_in) {
        this.l_1st_in = l_1st_in;
    }

    public Integer getL_1st_won() {
        return l_1st_won;
    }

    public void setL_1st_won(Integer l_1st_won) {
        this.l_1st_won = l_1st_won;
    }

    public Integer getL_2nd_won() {
        return l_2nd_won;
    }

    public void setL_2nd_won(Integer l_2nd_won) {
        this.l_2nd_won = l_2nd_won;
    }

    public Integer getL_sv_gms() {
        return l_sv_gms;
    }

    public void setL_sv_gms(Integer l_sv_gms) {
        this.l_sv_gms = l_sv_gms;
    }

    public Integer getL_bp_saved() {
        return l_bp_saved;
    }

    public void setL_bp_saved(Integer l_bp_saved) {
        this.l_bp_saved = l_bp_saved;
    }

    public Integer getL_bp_faced() {
        return l_bp_faced;
    }

    public void setL_bp_faced(Integer l_bp_faced) {
        this.l_bp_faced = l_bp_faced;
    }

    public Integer getWinner_rank() {
        return winner_rank;
    }

    public void setWinner_rank(Integer winner_rank) {
        this.winner_rank = winner_rank;
    }

    public Integer getWinner_rank_points() {
        return winner_rank_points;
    }

    public void setWinner_rank_points(Integer winner_rank_points) {
        this.winner_rank_points = winner_rank_points;
    }

    public Integer getLoser_rank() {
        return loser_rank;
    }

    public void setLoser_rank(Integer loser_rank) {
        this.loser_rank = loser_rank;
    }

    public Integer getLoser_rank_points() {
        return loser_rank_points;
    }

    public void setLoser_rank_points(Integer loser_rank_points) {
        this.loser_rank_points = loser_rank_points;
    }

    public Integer l_svpt;
    public Integer l_1st_in;
    public Integer l_1st_won;
    public Integer l_2nd_won;
    public Integer l_sv_gms;
    public Integer l_bp_saved;
    public Integer l_bp_faced;
    public Integer winner_rank;
    public Integer winner_rank_points;
    public Integer loser_rank;
    public Integer loser_rank_points;



}
