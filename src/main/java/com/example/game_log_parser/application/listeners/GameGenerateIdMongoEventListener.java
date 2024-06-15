package com.example.game_log_parser.application.listeners;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.example.game_log_parser.infra.database.models.GameModel;
import com.example.game_log_parser.infra.database.repositories.DatabaseSequenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameGenerateIdMongoEventListener extends AbstractMongoEventListener<GameModel> {


    @Autowired
    private DatabaseSequenceRepository sequenceGenerator;

    @Override
    public void onBeforeConvert(@SuppressWarnings("null") BeforeConvertEvent<GameModel> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(
                 sequenceGenerator.generateSequence(GameModel.SEQUENCE_NAME));
        }
    }
}