package com.logmein.cards.controller;

import com.logmein.cards.model.Game;
import com.logmein.cards.service.api.CardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class CardGameEndpoint {

    private final CardGameService cardGameService;

    @Autowired
    public CardGameEndpoint(CardGameService cardGameService) {
        this.cardGameService = cardGameService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Game game) {
        cardGameService.createGame(game);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/delete/{gameId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable long gameId) throws IllegalStateException {
        cardGameService.deleteGame(gameId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
