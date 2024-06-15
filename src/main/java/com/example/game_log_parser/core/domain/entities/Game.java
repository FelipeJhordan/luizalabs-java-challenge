package com.example.game_log_parser.core.domain.entities;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private long id;
    private int totalKills; 
    private List<String> players;   
    private Map<String, Integer> kills;
}
