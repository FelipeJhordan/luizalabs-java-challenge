package com.example.game_log_parser.core;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;
import com.example.game_log_parser.core.domain.usecases.GetGamesByIdUsecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetGamesByIdUsecaseTest {

    @Mock
    private IGameRepository gameRepository;

    private GetGamesByIdUsecase getGamesByIdUsecase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getGamesByIdUsecase = new GetGamesByIdUsecase(gameRepository);
    }

    @Test
    public void shouldReturnGameById() {
        long gameId = 1L;
        Game expectedGame =  Game.builder().id(gameId).build();

        when(gameRepository.findById(gameId)).thenReturn(expectedGame);

        Game result = getGamesByIdUsecase.execute(gameId);

        assertEquals(expectedGame, result);
    }
}