package com.logmein.cards.service.impl;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Deck;
import com.logmein.cards.model.Suit;
import com.logmein.cards.model.SuitValues;
import com.logmein.cards.service.api.DeckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {

    @Override
    public Deck createDeck() {
        List<Card> result = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (SuitValues suitValues : SuitValues.values()) {
                result.add(new Card(suit, suitValues));
            }
        }

        return new Deck(result);
    }
}
