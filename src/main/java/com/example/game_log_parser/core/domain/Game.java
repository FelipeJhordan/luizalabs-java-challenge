package com.example.game_log_parser.core.domain;

import java.util.List;

import lombok.Data;

@Data()
public class Game {
    private int id;
    private int total_kills;    
    private List<Player> players;
}
