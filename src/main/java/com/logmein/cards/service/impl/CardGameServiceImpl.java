package com.logmein.cards.service.impl;

import com.logmein.cards.service.api.CardGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CardGameServiceImpl implements CardGameService {

    public static final Logger logger = LoggerFactory.getLogger(CardGameServiceImpl.class.getName());

    @Override
    public void createGame() {

    }
}
