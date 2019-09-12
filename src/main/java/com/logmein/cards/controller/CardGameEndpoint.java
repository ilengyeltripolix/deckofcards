package com.logmein.cards.controller;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.model.Suit;
import com.logmein.cards.service.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class CardGameEndpoint {

    private final GameService gameService;

    @Autowired
    public CardGameEndpoint(GameService gameService) {
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

    @RequestMapping(value = "/add-player/{gameId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> addPlayerToGame(@PathVariable long gameId, @RequestBody Player player) {
        Game resultGame = gameService.addPlayerToGame(gameId, player);

        return resultGame != null ? ResponseEntity.status(HttpStatus.OK).body(resultGame) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    //TODO: remove player, deal

    @RequestMapping(value = "/get-player-cards/{gameId}/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getPlayerCards(@PathVariable long gameId, @PathVariable long playerId) {
        Optional<List<Card>> playerCards = gameService.getPlayerCards(gameId, playerId);

        return playerCards
                .map(cardList -> ResponseEntity.status(HttpStatus.OK).body(cardList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/sorted-player-list/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> getSortedPlayerList(@PathVariable long gameId) {
        Optional<List<Player>> sortedPlayerList = gameService.getSortedPlayerList(gameId);

        return sortedPlayerList
                .map(cardList -> ResponseEntity.status(HttpStatus.OK).body(cardList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/undealt-suit/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Suit, Integer>> getUndealtSuits(@PathVariable long gameId) {
        Optional<Map<Suit, Integer>> undealtSuit = gameService.getUndealtSuit(gameId);

        return undealtSuit
                .map(suits -> ResponseEntity.status(HttpStatus.OK).body(suits))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
