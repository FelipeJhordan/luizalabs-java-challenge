package com.example.game_log_parser.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.example.game_log_parser.core.domain.usecases.ProcessLinesAndSaveUsecase;
import com.example.game_log_parser.presentation.controllers.FileController;
import com.example.game_log_parser.presentation.dtos.UploadSuccessMessageDto;

public class FileControllerTest {



    @Test
    public void shouldUploadFile() throws IOException {
        ProcessLinesAndSaveUsecase usecase = mock(ProcessLinesAndSaveUsecase.class);
        FileController controller = new FileController(usecase);
        MultipartFile file = new MockMultipartFile("test.log", "Test log content".getBytes());

        ResponseEntity<UploadSuccessMessageDto> response = controller.uploadFile(file);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getMessage().equals("File uploaded successfully");
        verify(usecase, times(1)).execute(any(InputStream.class));
    }

    @Test
    public void shouldReturnInternalServerErrorWhenFileIsNotRead() throws IOException {
        ProcessLinesAndSaveUsecase usecase = mock(ProcessLinesAndSaveUsecase.class);
        FileController controller = new FileController(usecase);
        MultipartFile file = new MockMultipartFile("test.log", "Test log content".getBytes()) {
            @Override
            public InputStream getInputStream() throws IOException {
                throw new IOException("Failed to read file");
            }
        };

        ResponseEntity<UploadSuccessMessageDto> response = controller.uploadFile(file);

        assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        assert response.getBody() == null;
        verify(usecase, never()).execute(any(InputStream.class));
    }
}