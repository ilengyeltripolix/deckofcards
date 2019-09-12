package com.logmein.cards.service.api;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.model.Suit;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GameService {

    void createGame(Game game);

    void deleteGame(long gameId);

    /**
     * Adding a deck for an existing game
     *
     * @param gameId the specified id for game
     * @return game object with the newly added deck for show the actual game state
     */
    Game addDeckToGame(long gameId);

    /**
     * Adding a player for an existing game
     *
     * @param gameId the specified id for game
     * @param player player to add
     * @return game object with the newly added player for show the actual game state
     */
    Game addPlayerToGame(long gameId, Player player);

    Optional<List<Card>> getPlayerCards(long gameId, long playerId);

    Optional<List<Player>> getSortedPlayerList(long gameId);

    Optional<Map<Suit, Integer>> getUndealtSuit(long gameId);
}
