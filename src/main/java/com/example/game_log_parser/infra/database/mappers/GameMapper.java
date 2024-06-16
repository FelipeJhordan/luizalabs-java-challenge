package com.example.game_log_parser.infra.database.mappers;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.infra.database.models.GameModel;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameMapper {

    public Game toDomain(final GameModel gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .totalKills(gameEntity.getTotalKills())
                .players(gameEntity.getPlayers())
                .kills(gameEntity.getKills())
                .build();
    }

    public GameModel ofDomain(final Game game) {
        return GameModel.builder()
                .id(game.getId())
                .totalKills(game.getTotalKills())
                .players(game.getPlayers())
                .kills(game.getKills())
                .build();
    }
}