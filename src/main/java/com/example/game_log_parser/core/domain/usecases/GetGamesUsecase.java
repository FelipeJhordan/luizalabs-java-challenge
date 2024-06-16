package com.example.game_log_parser.core.domain.usecases;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Service
public class GetGamesUsecase {
    @Autowired
    private final IGameRepository gameRepository;
  
    public List<Game> execute() {
        return gameRepository.findAll();
    }
}
