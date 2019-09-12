package com.logmein.cards.controller;

import com.logmein.cards.service.api.CardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class CardGameEndpoint {

    private final CardGameService cardGameService;

    @Autowired
    public CardGameEndpoint(CardGameService cardGameService) {
        this.cardGameService = cardGameService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getPersonalData() throws IllegalStateException {
        cardGameService.createGame();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
