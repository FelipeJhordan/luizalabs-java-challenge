package com.example.game_log_parser.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data()
@AllArgsConstructor()
public class UploadSuccessMessageDto {
    private String message;
    @JsonProperty("games_processed")
    private int gamesProcessed;
}
