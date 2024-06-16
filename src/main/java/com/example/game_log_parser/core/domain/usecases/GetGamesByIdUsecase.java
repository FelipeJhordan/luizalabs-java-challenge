package com.example.game_log_parser.core.domain.usecases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GetGamesByIdUsecase {

    @Autowired
    private final IGameRepository gameRepository;
    
    public Game execute(long id) {
        return gameRepository.findById(id);
    }
}
