package com.example.game_log_parser.core.domain.usecases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProcessLinesAndSaveUsecase{

    @Autowired
    private final IGameRepository gameRepository;

    public int execute(InputStream file) throws IOException {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(file));
        List<Game> games = new ArrayList<>();
        Game currentGame = null;
        

        while (reader.ready()) {
            final var line = reader.readLine();
             if (line.contains("InitGame")) {
                    currentGame = new Game();
                    games.add(currentGame);
                }
                if (currentGame != null) {
                    currentGame.addLogLine(line);
                }
                if (line.contains("ShutdownGame")) {
                    currentGame = null;
                }

            
        }

        return gameRepository.saveBulk(games);

    }

    
}
