package com.example.game_log_parser.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data()
@AllArgsConstructor()
public class UploadSuccessMessageDto {
    private String message;
    private int games_processed;
}
