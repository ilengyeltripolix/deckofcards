package com.logmein.cards.service.impl;

import com.logmein.cards.model.Game;
import com.logmein.cards.service.api.CardGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardGameServiceImpl implements CardGameService {

    private static final Logger logger = LoggerFactory.getLogger(CardGameServiceImpl.class.getName());

    private Map<Long, Game> gameHolder;

    public CardGameServiceImpl() {
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
}
