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

    private Set<Player> players;

    private List<Deck> decks;

    public Game() {
        this.players = new HashSet<>();
        this.decks = new ArrayList<>();
    }
}
