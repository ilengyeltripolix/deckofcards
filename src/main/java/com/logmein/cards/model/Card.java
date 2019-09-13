package com.logmein.cards.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Card implements Comparable<Card>{

    private Suit suit;
    private SuitValues value;

    @Override
    public int compareTo(Card card) {
        return Integer.compare(card.suit.getValue() + (card.value.getValue()),
                this.suit.getValue() + (this.value.getValue()));
    }
}
