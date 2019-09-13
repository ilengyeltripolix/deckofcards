package com.logmein.cards.service.api;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.model.Suit;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GameService {

    /**
     * Creates a game
     *
     * @param game initial game object
     */
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

    /**
     * Delete a player from an existing game
     *
     * @param gameId   the specified id for game
     * @param playerId player id to remove
     * @return game object without the  player for show the actual game state
     */
    Game removePlayerFromGame(long gameId, long playerId);

    /**
     * Get the list of cards for a player
     *
     * @param gameId   the specified id for game
     * @param playerId selected player id
     * @return card list for the player
     */
    Optional<List<Card>> getPlayerCards(long gameId, long playerId);

    /**
     * Deal cards to a player in a game from the game deck
     *
     * @param gameId   the specified id for game
     * @param playerId selected player id
     * @return game object for show the actual game state
     */
    Optional<Game> dealCard(long gameId, long playerId);

    Optional<List<Player>> getSortedPlayerList(long gameId);

    /**
     * Get the count of how many cards per suit are left undealt in the game deck
     *
     * @param gameId the specified id for game
     * @return game object for show the actual game state
     */
    Optional<Map<Suit, Integer>> getUnDealtSuit(long gameId);

    /**
     * Get the count of each card (suit and value) remaining in the game deck sorted by suit
     *
     * @param gameId the specified id for game
     * @return game object for show the actual game state
     */
    Optional<Map<Card, Integer>> getRemainingCards(long gameId);

    /**
     * Shuffle the game deck (shoe)
     */
    void shuffleDeck(long gameId);
}
