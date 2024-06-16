package com.example.game_log_parser.core.domain.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Game {
    private long id;
    private int totalKills;
    private Set<String> players;
    private Map<String, Integer> kills;

    public Game() {
        this.totalKills = 0;
        this.players = new HashSet<>();
        this.kills = new HashMap<>();
    }

    public void addLogLine(String line) {
        if (line.contains("ClientUserinfoChanged")) {
            String playerName = extractPlayerName(line);
            players.add(playerName);
            if (!kills.containsKey(playerName)) {
                kills.put(playerName, 0);
            }
        } else if (line.contains("Kill")) {
            totalKills++;
            String killer = extractKiller(line);
            String victim = extractVictim(line);

            boolean isSuicide = killer.equals(victim);
            if(isSuicide) {
                return;
            }

            boolean  killerExistsAndNoIsWorld  = killer != null && !killer.equals("<world>");
            if (killerExistsAndNoIsWorld) {
                kills.put(killer, kills.getOrDefault(killer, 0) + 1);
            }

            boolean victimExistsAndKillerIsWorld = victim != null && killer != null && killer.equals("<world>");
            if (victimExistsAndKillerIsWorld) {
                kills.put(victim, kills.getOrDefault(victim, 0) - 1);
            }
        }
    }

    private String extractPlayerName(String line) {
        int start = line.indexOf("n\\") + 2;
        int end = line.indexOf("\\t\\");
        return line.substring(start, end);
}

    private String extractKiller(String line) {
        String[] parts = line.split(": ");
 
        if (parts.length > 1) {
            String[] killInfo = parts[2].split("killed");
            return killInfo[0].trim();
        }
        return null;
    }

    private String extractVictim(String line) {
        String[] parts = line.split("killed ");
        
        if (parts.length > 1) {
            String[] killInfo = parts[1].split(" by");
            return killInfo[0].trim();
        }
        return null;
    }

}