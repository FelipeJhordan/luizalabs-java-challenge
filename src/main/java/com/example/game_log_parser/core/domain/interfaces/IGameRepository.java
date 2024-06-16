package com.example.game_log_parser.core.domain.interfaces;

import java.util.List;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.infra.database.models.GameModel;

public interface IGameRepository {
    Game findById(long id);
    List<Game> findAll();
    void saveBulk(List<GameModel> games);
}
