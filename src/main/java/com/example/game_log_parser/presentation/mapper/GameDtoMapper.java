package com.example.game_log_parser.presentation.mapper;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.presentation.dtos.GameDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameDtoMapper {
    public static GameDto toDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId((int) game.getId());
        gameDto.setTotalKills(game.getTotalKills());
        gameDto.setPlayers(game.getPlayers());
        gameDto.setKills(game.getKills());
        return gameDto;
    }
}
