package com.logmein.cards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {

    private List<Player> players;

    private List<Deck> decks;

}
