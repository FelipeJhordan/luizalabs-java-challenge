package com.example.game_log_parser.infra.database;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.infra.database.mappers.GameMapper;
import com.example.game_log_parser.infra.database.models.GameModel;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameMapperTest {

    @Test
    public void shouldReturnGameAfterCallOfModel() {
        GameModel gameModel = GameModel.builder()
                .id(1)
                .totalKills(10)
                .players(
                    Set.of(
                        "Player1",
                        "Player2"
                    )
                )
                .kills(Map.of(
                    "Player1", 5,
                    "Player2", 3
                ))
                .build();

        Game game = GameMapper.toDomain(gameModel);

        Assertions.assertEquals(1, game.getId());
        Assertions.assertEquals(10, game.getTotalKills());
        Assertions.assertEquals(gameModel.getPlayers(), game.getPlayers());
        Assertions.assertEquals(gameModel.getKills(), game.getKills());
    }

    @Test
    public void shouldReturnGameModelAfterCallOfDomain() {
        Game game = Game.builder()
                .id(1)
                .totalKills(10)
                .players(
                    Set.of(
                        "Player1",
                        "Player2"
                    )
                )
                .kills(Map.of(
                    "Player1", 5,
                    "Player2", 3
                ))
                .build();

        GameModel gameModel = GameMapper.ofDomain(game);

        Assertions.assertEquals(1, gameModel.getId());
        Assertions.assertEquals(10, gameModel.getTotalKills());
        Assertions.assertEquals(game.getPlayers(), gameModel.getPlayers());
        Assertions.assertEquals(game.getKills(), gameModel.getKills());
    }
}