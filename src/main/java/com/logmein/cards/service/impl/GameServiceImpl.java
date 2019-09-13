package com.logmein.cards.service.impl;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.model.Suit;
import com.logmein.cards.service.api.DeckService;
import com.logmein.cards.service.api.GameService;
import com.logmein.cards.service.api.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        logger.info("Game created with id: {}", game.getId());

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
            game.getGameDeck().addAll(deckService.createDeck());

            logger.info("New deck was added to game with id: {}", gameId);
        }

        return game;
    }

    @Override
    public Game addPlayerToGame(long gameId, Player player) {
        Game game = gameHolder.get(gameId);

        if (game != null) {
            game.getPlayers().add(player);

            logger.info("Player with id: {} was added to game", player.getId());
        }

        return game;
    }

    @Override
    public Game removePlayerFromGame(long gameId, long playerId) {
        Game game = gameHolder.get(gameId);

        Optional<Player> foundPlayer = game.getPlayers()
                .stream()
                .filter(player -> player.getId() == playerId)
                .findFirst();

        foundPlayer.ifPresent(player -> game.getPlayers().remove(player));

        return game;
    }

    @Override
    public Optional<List<Card>> getPlayerCards(long gameId, long playerId) {
        Game game = gameHolder.get(gameId);

        Optional<List<Card>> result = Optional.empty();
        if (game != null) {
            result = playerService.getPlayerCards(game, playerId);
        }

        return result;
    }

    @Override
    public Optional<Game> dealCard(long gameId, long playerId) {
        Game game = gameHolder.get(gameId);

        if (game != null && !game.getGameDeck().isEmpty()) {
            Card card = game.getGameDeck().get(0);
            game.getGameDeck().remove(0);

            Optional<Player> foundPlayer = game.getPlayers()
                    .stream()
                    .filter(player -> player.getId() == playerId)
                    .findFirst();

            foundPlayer.ifPresent(player -> {
                player.getCards().add(card);
                player.addSumValue(card.getValue().getValue());
            });

        }

        return Optional.ofNullable(game);
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
        game.getGameDeck().forEach(card -> resultCardsBySuit.put(card.getSuit(),
                resultCardsBySuit.get(card.getSuit()) != null ? resultCardsBySuit.get(card.getSuit()) + 1 : 1));

        return Optional.of(resultCardsBySuit);
    }

    @Override
    public Optional<Map<Card, Integer>> getRemainingCards(long gameId) {
        Game game = gameHolder.get(gameId);

        Map<Card, Integer> remainingCards = new HashMap<>();

        List<Card> cardList = new ArrayList<>(game.getGameDeck());
        Collections.sort(cardList);

        for (Card card : cardList) {
            remainingCards.put(card,
                    remainingCards.get(card) != null ? remainingCards.get(card) + 1 : 1);
        }

        return Optional.of(remainingCards);
    }

    @Override
    public void shuffleDeck(long gameId) {
        Game game = gameHolder.get(gameId);

        game.setGameDeck(deckService.shuffleDeck(game.getGameDeck()));
    }
}
