package com.example.game_log_parser.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;
import com.example.game_log_parser.core.domain.usecases.ProcessLinesAndSaveUsecase;


public class ProcessLinesAndSaveUsecaseTest {

    @Mock
    private IGameRepository gameRepository;

    private ProcessLinesAndSaveUsecase usecase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        usecase = new ProcessLinesAndSaveUsecase(gameRepository);
    }

    @Test
    public void shouldSaveGameWhenHasCorrectlyInstructions() throws IOException {
        String logData = "InitGame\nLog line 1\nLog line 2\nShutdownGame\nShutdownGame\n";
        InputStream inputStream = new ByteArrayInputStream(logData.getBytes());
        List<Game> expectedGames = new ArrayList<>();
        Game game1 = new Game();
        game1.addLogLine("Log line 1");
        game1.addLogLine("Log line 2");
        expectedGames.add(game1);


        when(gameRepository.saveBulk(expectedGames)).thenReturn(2);

        int result = usecase.execute(inputStream);

        assertEquals(2, result);
        verify(gameRepository, times(1)).saveBulk(expectedGames);
    }

  

    @Test
    public void shouldNotSaveGameWhenHasNoInstructions() throws IOException {
        String logData = "";
        InputStream inputStream = new ByteArrayInputStream(logData.getBytes());

        int result = usecase.execute(inputStream);

        assertEquals(0, result);
        verify(gameRepository, never()).saveBulk(anyList());
    }
}