package com.logmein.cards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Deck {

    private List<Card> cards;

    public Deck() {
        //TODO deck creation with values
        this.cards = new ArrayList<>();
    }
}
