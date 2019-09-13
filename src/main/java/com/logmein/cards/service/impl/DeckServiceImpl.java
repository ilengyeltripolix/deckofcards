package com.logmein.cards.service.impl;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Suit;
import com.logmein.cards.model.SuitValues;
import com.logmein.cards.service.api.DeckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeckServiceImpl implements DeckService {

    @Override
    public List<Card> createDeck() {
        List<Card> result = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (SuitValues suitValues : SuitValues.values()) {
                result.add(new Card(suit, suitValues));
            }
        }

        return result;
    }

    @Override
    public List<Card> shuffleDeck(List<Card> deck) {
        Random randomGenerator = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int index = randomGenerator.nextInt(i + 1);

            Card card = deck.get(index);
            deck.set(index, deck.get(i));
            deck.set(i, card);
        }

        return deck;
    }
}
