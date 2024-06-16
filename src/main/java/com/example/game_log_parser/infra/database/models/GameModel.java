package com.example.game_log_parser.infra.database.models;

import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "games")
public class GameModel {
    @Transient
    public static final String SEQUENCE_NAME = "game_sequence";

    @Id()
    private long id;

    private int totalKills;

    private Set<String> players;

    private Map<String, Integer> kills;
}
