package com.example.game_log_parser.presentation.dtos;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
public class GameDto {
    private int id;
    @JsonProperty("total_kills")
    private int totalKills;
    private Set<String> players;
    private Map<String, Integer> kills;
}