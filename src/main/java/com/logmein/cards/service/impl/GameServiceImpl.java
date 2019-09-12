package com.logmein.cards.service.impl;

import com.logmein.cards.model.*;
import com.logmein.cards.service.api.GameService;
import com.logmein.cards.service.api.DeckService;
import com.logmein.cards.service.api.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class.getName());

    private final DeckService deckService;

    private final PlayerService playerService;

    private Map<Long, Game> gameHolder;

    @Autowired
    public GameServiceImpl(DeckService deckService, PlayerService playerService) {
        this.deckService = deckService;
        this.playerService = playerService;

        this.gameHolder = new HashMap<>();
    }

    @Override
    public void createGame(Game game) {
        logger.info("Game added with id: {}", game.getId());

        gameHolder.put(game.getId(), game);
    }

    @Override
    public void deleteGame(long gameId) {
        gameHolder.remove(gameId);

        logger.info("Game was removed with id: {}", gameId);
    }

    @Override
    public Game addDeckToGame(long gameId) {
        Game game = gameHolder.get(gameId);

        if (game != null) {
            game.getDecks().add(deckService.createDeck());
        }

        return game;
    }

    @Override
    public Game addPlayerToGame(long gameId, Player player) {
        Game game = gameHolder.get(gameId);

        if (game != null) {
            game.getPlayers().add(player);
        }

        return game;
    }

    @Override
    public Optional<List<Card>> getPlayerCards(long gameId, long playerId) {
        Game game = gameHolder.get(gameId);

        return playerService.getPlayerCards(game, playerId);
    }

    @Override
    public Optional<List<Player>> getSortedPlayerList(long gameId) {
        Game game = gameHolder.get(gameId);

        List<Player> result = null;

        if (game != null) {
            result = playerService.getSortedPlayerList(game);
        }

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Map<Suit, Integer>> getUndealtSuit(long gameId) {
        Game game = gameHolder.get(gameId);

        Map<Suit, Integer> resultCardsBySuit = new HashMap<>();

        for (Deck deck : game.getDecks()) {
            for (Card card : deck.getCards()) {
                resultCardsBySuit.put(card.getSuit(),
                        resultCardsBySuit.get(card.getSuit()) != null ? resultCardsBySuit.get(card.getSuit()) + 1 : 1 );
            }
        }

        return Optional.of(resultCardsBySuit);
    }
}
