package com.logmein.cards.service.api;

import com.logmein.cards.model.Card;

import java.util.List;

public interface DeckService {

    /**
     * Creates a new deck without shuffling it
     *
     * @return created deck
     */
    List<Card> createDeck();

    List<Card> shuffleDeck(List<Card> cards);
}
