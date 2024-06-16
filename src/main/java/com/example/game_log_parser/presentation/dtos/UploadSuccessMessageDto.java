package com.example.game_log_parser.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data()
@AllArgsConstructor()
public class UploadSuccessMessageDto {
    @Schema(example = "File uploaded successfully", required = true, description = "The message")
    private String message;
    @Schema(example = "5", required = true, description = "The number of games processed")
    @JsonProperty("games_processed")
    private int gamesProcessed;
}
