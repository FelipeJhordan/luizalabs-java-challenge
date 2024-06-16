package com.example.game_log_parser.presentation;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.usecases.GetGamesByIdUsecase;
import com.example.game_log_parser.core.domain.usecases.GetGamesUsecase;
import com.example.game_log_parser.presentation.controllers.GameController;
import com.example.game_log_parser.presentation.dtos.GameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class GameControllerTest {

    private GameController gameController;

    @Mock
    private GetGamesUsecase getGamesUsecase;

    @Mock
    private GetGamesByIdUsecase getGamesByIdUsecase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(getGamesUsecase, getGamesByIdUsecase);
    }

    @Test
    public void shouldReturnAllGames() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());
        when(getGamesUsecase.execute()).thenReturn(games);

        ResponseEntity<List<GameDto>> response = gameController.getGames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(0, response.getBody().get(0).getId());
        verify(getGamesUsecase, times(1)).execute();
    }

    @Test
    public void shouldReturnGameById() {
        long gameId = 1L;
        Game game = new Game();
        when(getGamesByIdUsecase.execute(gameId)).thenReturn(game);

        ResponseEntity<GameDto> response = gameController.getGameById(gameId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getId());
        verify(getGamesByIdUsecase, times(1)).execute(gameId);
    }

    @Test
    public void shouldReturnNullIfGameNotFound() {
        long gameId = 1L;
        when(getGamesByIdUsecase.execute(gameId)).thenReturn(null);

        ResponseEntity<GameDto> response = gameController.getGameById(gameId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(getGamesByIdUsecase, times(1)).execute(gameId);
    }
}