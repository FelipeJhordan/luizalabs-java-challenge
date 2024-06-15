package com.example.game_log_parser.core.domain.entities;

import java.util.List;

import lombok.Data;

@Data()
public class Game {
    private long id;
    private int total_kills;    
    private List<Player> players;
}
