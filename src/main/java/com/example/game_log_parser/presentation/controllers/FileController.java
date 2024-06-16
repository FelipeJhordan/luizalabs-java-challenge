package com.example.game_log_parser.presentation.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.game_log_parser.core.domain.usecases.ProcessLinesAndSaveUsecase;
import com.example.game_log_parser.presentation.dtos.UploadSuccessMessageDto;

import lombok.AllArgsConstructor;

@RequestMapping("api/v1/file")
@RestController()
@AllArgsConstructor()
public class FileController {

    @Autowired()
    private final ProcessLinesAndSaveUsecase processLinesAndSaveUsecase;

    @PostMapping
    public ResponseEntity<UploadSuccessMessageDto> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        int gamesProcessed = processLinesAndSaveUsecase.execute(file.getInputStream());
        return ResponseEntity.ok(new UploadSuccessMessageDto("File uploaded successfully", gamesProcessed));
    }
}
