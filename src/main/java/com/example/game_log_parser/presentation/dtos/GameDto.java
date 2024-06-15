package com.example.game_log_parser.presentation.dtos;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
public class GameDto {
    private int id;
    @JsonProperty("total_kills")
    private int totalKills;
    private List<String> players;
    private Map<String, Integer> kills;
}