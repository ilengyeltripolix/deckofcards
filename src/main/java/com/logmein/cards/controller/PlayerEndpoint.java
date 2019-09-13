package com.logmein.cards.controller;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.service.api.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerEndpoint {

    private final GameService gameService;

    public PlayerEndpoint(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/add-player/{gameId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> addPlayerToGame(@PathVariable long gameId, @RequestBody Player player) {
        Game resultGame = gameService.addPlayerToGame(gameId, player);

        return resultGame != null ? ResponseEntity.status(HttpStatus.OK).body(resultGame) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/remove-player/{gameId}/{playerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> addPlayerToGame(@PathVariable long gameId, @PathVariable long playerId) {
        Game resultGame = gameService.removePlayerFromGame(gameId, playerId);

        return resultGame != null ? ResponseEntity.status(HttpStatus.OK).body(resultGame) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

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
}

