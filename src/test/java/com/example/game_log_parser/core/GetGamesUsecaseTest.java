package com.example.game_log_parser.core;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;
import com.example.game_log_parser.core.domain.usecases.GetGamesUsecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetGamesUsecaseTest {

    @Mock
    private IGameRepository gameRepository;

    private GetGamesUsecase getGamesUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getGamesUsecase = new GetGamesUsecase(gameRepository);
    }

    @Test
    void shouldReturnAllGames() {
        List<Game> expectedGames = new ArrayList<>();
        expectedGames.add(new Game());
        expectedGames.add(new Game());

        when(gameRepository.findAll()).thenReturn(expectedGames);

        List<Game> actualGames = getGamesUsecase.execute();

        assertEquals(expectedGames, actualGames);
        verify(gameRepository, times(1)).findAll();
    }
}