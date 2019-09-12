package com.logmein.cards.service.api;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Optional<List<Card>> getPlayerCards(Game game, long playerId);

    List<Player> getSortedPlayerList(Game game);
}
