package com.logmein.cards.service.api;

import com.logmein.cards.model.Game;

public interface CardGameService {

    void createGame(Game game);

    void deleteGame(long gameId);
}
