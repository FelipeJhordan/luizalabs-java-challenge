package com.example.game_log_parser.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.game_log_parser.presentation.dtos.UploadSuccessMessageDto;

@RequestMapping("api/v1/file")
@RestController()
public class FileController {

    @PostMapping
    public ResponseEntity<UploadSuccessMessageDto> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(new UploadSuccessMessageDto("File uploaded successfully", 0));
    }
}
