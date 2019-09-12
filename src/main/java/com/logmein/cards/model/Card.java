package com.logmein.cards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

    private Suit suit;
    private SuitValues value;
}
