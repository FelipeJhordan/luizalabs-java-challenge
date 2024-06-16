package com.example.game_log_parser.presentation.dtos;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data()
public class GameDto {
    @Schema(example = "1", required = true, description = "The Game ID")
    private int id;
    @JsonProperty("total_kills")
    @Schema(example = "5", required = true, description = "The total number of kills in the game")
    private int totalKills;
    @Schema(example = "[\"Isgalamido\", \"Dono da Bola\", \"Zeh\"]", required = true, description = "The players in the game")
    private Set<String> players;
    @Schema(example = "{\"Isgalamido\": 5, \"Dono da Bola\": 3, \"Zeh\": 1}", required = true, description = "The kills in the game")
    private Map<String, Integer> kills;
}