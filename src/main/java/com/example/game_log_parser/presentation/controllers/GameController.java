package com.example.game_log_parser.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game_log_parser.presentation.dtos.GameDto;

@RequestMapping("api/v1/game")
@RestController
public class GameController {
    @GetMapping()
    public ResponseEntity<List<GameDto>> getGames() {
        return ResponseEntity.ok(List.of(new GameDto(), new GameDto()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById() {
        return ResponseEntity.ok(new GameDto());
    }
}
