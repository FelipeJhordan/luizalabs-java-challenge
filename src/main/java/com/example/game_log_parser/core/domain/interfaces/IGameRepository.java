package com.example.game_log_parser.core.domain.interfaces;

import java.util.List;

import com.example.game_log_parser.core.domain.entities.Game;

public interface IGameRepository {
    Game findById(long id);
    List<Game> findAll();
    int saveBulk(List<Game> games);
}
