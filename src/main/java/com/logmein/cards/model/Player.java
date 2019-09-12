package com.logmein.cards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {

    private long id;
    private List<Card> cards;
    private int sumValue;
}
