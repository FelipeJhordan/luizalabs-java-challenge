package com.example.game_log_parser.presentation;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.presentation.dtos.GameDto;
import com.example.game_log_parser.presentation.mapper.GameDtoMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

public class GameDtoMapperTest {

    @Test
    public void shouldReturnGameDtoAfterCallOfModel() {
        Game game = new Game();
        game.setId(1L);
        game.setTotalKills(10);
        game.setPlayers(
            Set.of(
                "Player1",
                "Player2"
            )
        );
        game.setKills(Map.of(
            "Player1", 5,
            "Player2", 3
        ));

        GameDto gameDto = GameDtoMapper.toDto(game);

        Assertions.assertEquals(1, gameDto.getId());
        Assertions.assertEquals(10, gameDto.getTotalKills());
        Assertions.assertEquals(game.getPlayers(), gameDto.getPlayers());
        Assertions.assertEquals(game.getKills(), gameDto.getKills());
    }
    
    @Test
    public void shouldReturnGameDtoWithDefaultValues() {
        Game game = new Game();

        GameDto gameDto = GameDtoMapper.toDto(game);

        Assertions.assertEquals(0, gameDto.getId());
        Assertions.assertEquals(0, gameDto.getTotalKills());
        Assertions.assertEquals(0, gameDto.getPlayers().size());
        Assertions.assertEquals(0, gameDto.getKills().size());
    }
}