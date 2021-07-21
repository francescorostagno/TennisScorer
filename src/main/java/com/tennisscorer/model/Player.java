package com.tennisscorer.model;

import javax.persistence.*;

@Entity
@Table(name = "`player`")
@NamedQueries({
        @NamedQuery(
                name = Player.FIND_BY_NAME,
                query = "select p from Player p where player_name = ?1"
        )
})
public class Player {

    public static final String FIND_BY_NAME = "Player.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name =  "`player_name`")
    private String playerName;

    public Player (String player_name){
        this.playerName = player_name;
    }

    public Player(Long id, String player_name){
        this.id = id;
        this.playerName = player_name;
    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return playerName;
    }

    public void setPlayer_name(String player_name) {
        this.playerName = player_name;
    }
}
