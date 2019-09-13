package com.logmein.cards.model;

import lombok.Getter;

@Getter
public enum Suit {
    HEARTS(4),
    SPADES(3),
    CLUBS(2),
    DIAMONDS(1);

    private int value;

    Suit(int value) {
        this.value = value;
    }
}
