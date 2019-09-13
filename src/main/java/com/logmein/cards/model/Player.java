package com.logmein.cards.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Player {

    private long id;
    private List<Card> cards;
    private int sumValue;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public void addSumValue(int value){
        this.sumValue += value;
    }

}
