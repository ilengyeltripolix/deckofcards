package com.logmein.cards.service.impl;

import com.logmein.cards.model.Card;
import com.logmein.cards.model.Game;
import com.logmein.cards.model.Player;
import com.logmein.cards.service.api.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Override
    public Optional<List<Card>> getPlayerCards(Game game, long playerId) {
        Player foundPlayer = game
                .getPlayers()
                .stream()
                .filter(player -> player.getId() == playerId)
                .findFirst()
                .get();

        return Optional.ofNullable(foundPlayer.getCards());
    }

    @Override
    public List<Player> getSortedPlayerList(Game game) {
        List<Player> playerList = new ArrayList<>(game.getPlayers());
        playerList
                .sort(comparingInt(Player::getSumValue)
                        .reversed());

        return playerList;
    }
}
