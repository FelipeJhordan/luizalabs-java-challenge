package com.example.game_log_parser.infra.database;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.infra.database.models.GameModel;
import com.example.game_log_parser.infra.database.repositories.GameRepositoryImp;
import com.example.game_log_parser.infra.database.repositories.interfaces.IGameMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameRepositoryImpTest {

    @Mock
    private IGameMongoRepository mongoRepository;

    @Mock
    private GameRepositoryImp gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameRepository = new GameRepositoryImp(mongoRepository);
    }

    @Test
    void shouldReturnTheGameAfterCallFindById() {
        long gameId = 1L;
        GameModel gameModel = GameModel.builder().build();
        gameModel.setId(gameId);
        when(mongoRepository.findById(gameId)).thenReturn(Optional.of(gameModel));
        Game result = gameRepository.findById(gameId);
        assertEquals(gameId, result.getId());
    }

    @Test
    void shouldReturnNullAfterCallFindById() {
        long gameId = 1L;
        when(mongoRepository.findById(gameId)).thenReturn(Optional.empty());

        Game result = gameRepository.findById(gameId);

        assertEquals(null, result);
    }

    @Test
    void shouldReturnAllGames() {
        List<GameModel> gameModels = new ArrayList<>();
        gameModels.add(GameModel.builder().build());
        gameModels.add(GameModel.builder().build());
        when(mongoRepository.findAll()).thenReturn(gameModels);

        List<Game> result = gameRepository.findAll();

        assertEquals(gameModels.size(), result.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnTheSizeOfTheListAfterSaveBulk() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());
        List<GameModel> gameModels = new ArrayList<>();
        gameModels.add(GameModel.builder().build());
        gameModels.add(GameModel.builder().build());
        when(mongoRepository.saveAll(
                any(List.class)
        )).thenReturn(gameModels);

        int result = gameRepository.saveBulk(games);

        assertEquals(gameModels.size(), result);
    }
}