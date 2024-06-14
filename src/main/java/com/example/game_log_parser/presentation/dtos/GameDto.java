package com.example.game_log_parser.presentation.dtos;

import java.util.List;
import java.util.Map;

public class GameDto {
    private int id;
    private int total_kills;
    private List<String> players;
    private Map<String, Integer> kills;
}