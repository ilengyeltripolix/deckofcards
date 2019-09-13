package com.logmein.cards.controller;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Suit;
import com.logmein.cards.service.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameEndpoint {

    private final GameService gameService;

    @Autowired
    public GameEndpoint(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Game game) {
        gameService.createGame(game);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/delete/{gameId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable long gameId) {
        gameService.deleteGame(gameId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/add-deck/{gameId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> addDeckToGame(@PathVariable long gameId) {
        Game resultGame = gameService.addDeckToGame(gameId);

        return resultGame != null ? ResponseEntity.status(HttpStatus.OK).body(resultGame) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/deal-card/{gameId}/{playerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> dealCard(@PathVariable long gameId, @PathVariable long playerId) {
        Optional<Game> resultGame = gameService.dealCard(gameId, playerId);

        return resultGame
                .map(game -> ResponseEntity.status(HttpStatus.OK).body(game))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/undealt-suit/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Suit, Integer>> getUnDealtSuits(@PathVariable long gameId) {
        Optional<Map<Suit, Integer>> unDealtSuit = gameService.getUnDealtSuit(gameId);

        return unDealtSuit
                .map(suits -> ResponseEntity.status(HttpStatus.OK).body(suits))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/remaining-cards/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Card, Integer>> getRemainingCards(@PathVariable long gameId) {
        Optional<Map<Card, Integer>> unDealtSuit = gameService.getRemainingCards(gameId);

        return unDealtSuit
                .map(suits -> ResponseEntity.status(HttpStatus.OK).body(suits))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/shuffle-cards/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> shuffleCards(@PathVariable long gameId) {
        gameService.shuffleDeck(gameId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
