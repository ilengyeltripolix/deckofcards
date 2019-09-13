package com.logmein.cards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Game {

    private long id;

    private List<Player> players;

    private List<Card> gameDeck;

    public Game() {
        this.players = new ArrayList<>();
        this.gameDeck = new ArrayList<>();
    }
}
