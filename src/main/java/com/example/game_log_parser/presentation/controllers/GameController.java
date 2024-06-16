package com.example.game_log_parser.presentation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.usecases.GetGamesByIdUsecase;
import com.example.game_log_parser.core.domain.usecases.GetGamesUsecase;
import com.example.game_log_parser.presentation.dtos.GameDto;
import com.example.game_log_parser.presentation.mapper.GameDtoMapper;

import lombok.AllArgsConstructor;

@RequestMapping("api/v1/game")
@RestController
@AllArgsConstructor
public class GameController {
    private final GetGamesUsecase getGamesUsecase;
    private final GetGamesByIdUsecase getGamesByIdUsecase;

    @GetMapping()
    public ResponseEntity<List<GameDto>> getGames() {
        List<GameDto> gamesDtos = getGamesUsecase.execute().stream().map(GameDtoMapper::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(gamesDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable final long id) {
        Game game = getGamesByIdUsecase.execute(id);
        
        if(game == null) {
            return ResponseEntity.notFound().build();
        }
        
        GameDto gameDto = GameDtoMapper.toDto(game);
        
        return ResponseEntity.ok(gameDto);
    }
}
