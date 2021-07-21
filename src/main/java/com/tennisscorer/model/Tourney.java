package com.tennisscorer.model;

import javax.persistence.*;

@Entity
@Table(name = "`tourney`")
public class Tourney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`tourney_id`", unique = true)
    private String tourneyId;

    @Column(name = "`tourney_name`")
    private String tourney_name;

    @Column(name = "`tourney_date`")
    private String tourney_date;

    @Column(name = "`draw_size`")
    private Long draw_size;

    @Column(name = "`level`")
    private String level;

    @Column(name = "`surface`")
    private String surface;

    public Tourney(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourney_id() {
        return tourneyId;
    }

    public void setTourney_id(String tourney_id) {
        this.tourneyId = tourney_id;
    }

    public String getTourney_name() {
        return tourney_name;
    }

    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public Long getDraw_size() {
        return draw_size;
    }

    public void setDraw_size(Long draw_size) {
        this.draw_size = draw_size;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Tourney(String tourney_id, String tourney_name, String tourney_date, Long draw_size, String level, String surface){
        this.tourneyId = tourney_id;
        this.tourney_name = tourney_name;
        this.tourney_date = tourney_date;
        this.draw_size = draw_size;
        this.level = level;
        this.surface = surface;
    }
}
