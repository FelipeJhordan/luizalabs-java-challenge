package com.example.game_log_parser.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.game_log_parser.core.domain.entities.Game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void shouldAddThePlayerWhenConnect() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        game.addLogLine(line);

        Set<String> expectedPlayers = new HashSet<>();
        expectedPlayers.add("Player1");

        Map<String, Integer> expectedKills = new HashMap<>();
        expectedKills.put("Player1", 0);

        Assertions.assertEquals(0, game.getTotalKills());
        Assertions.assertEquals(expectedPlayers, game.getPlayers());
        Assertions.assertEquals(expectedKills, game.getKills());
    }

    @Test
    public void shouldAddPlayersWhenConnectAddIncrementKillsWhenPlayerSuicide() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        game.addLogLine(line);
        line = "ClientUserinfoChanged: 2 n\\Player2\\t\\";
        game.addLogLine(line);

        line = "Kill: 2 2 1: Player1 killed Player1 by MOD_UNKNOWN";
        game.addLogLine(line);

        Assertions.assertEquals(1, game.getTotalKills());
        Assertions.assertFalse(game.getPlayers().isEmpty());
        Assertions.assertTrue(!game.getKills().isEmpty());
    }

    @Test
    public void shouldAddPlayersWhenConnectAddIncrementKillsWhenPlayerKillOther() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        game.addLogLine(line);
        line = "ClientUserinfoChanged: 2 n\\Player2\\t\\";
        game.addLogLine(line);
        line = " 12:26 Kill: 3 2 7: Player1 killed Player2 by MOD_ROCKET_SPLASH";
        game.addLogLine(line);

        Set<String> expectedPlayers = new HashSet<>();
        expectedPlayers.add("Player1");
        expectedPlayers.add("Player2");

        Map<String, Integer> expectedKills = new HashMap<>();
        expectedKills.put("Player1", 1);
        expectedKills.put("Player2", 0);

        Assertions.assertEquals(1, game.getTotalKills());
        Assertions.assertEquals(expectedPlayers, game.getPlayers());
        Assertions.assertEquals(expectedKills, game.getKills());
    }

    @Test
    public void  shouldAddPlayersWhenConnectAddIncrementKillsWhenPlayerKillOtherAndPlayerIsWorld() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        game.addLogLine(line);
    
        line = "Kill: 2 2 1: <world> killed Player1 by MOD_UNKNOWN";
        game.addLogLine(line);

        Set<String> expectedPlayers = new HashSet<>();
        expectedPlayers.add("Player1");

        Map<String, Integer> expectedKills = new HashMap<>();
        expectedKills.put("Player1", -1);

        Assertions.assertEquals(1, game.getTotalKills());
        Assertions.assertEquals(expectedPlayers, game.getPlayers());
        Assertions.assertEquals(expectedKills, game.getKills());
    }

    @Test
    public void shouldExtractPlayerNameCorrectlyWhenUserInfoChanged() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        String playerName = game.extractPlayerName(line);

        Assertions.assertEquals("Player1", playerName);
    }

    @Test
    public void shouldExtractKillerCorrectly() {
        String line = "Kill: 2 2 1: Player1 killed Player2 by MOD_UNKNOWN";
        String killer = game.extractKiller(line);

        Assertions.assertEquals("Player1", killer);
    }

    @Test
    public void shouldExtractVictimCorrectly() {
        String line = "Kill: 2 2 1: Player1 killed Player2 by MOD_UNKNOWN";
        String victim = game.extractVictim(line);

        Assertions.assertEquals("Player2", victim);
    }

    @Test
    public void shouldNoThrowWhenNoFindKiller() {
        String line = "ClientUserinfoChanged: 2 n\\Player1\\t\\";
        game.addLogLine(line);
        line = "ClientUserinfoChanged: 2 n\\Player2\\t\\";
        game.addLogLine(line);
        line = "Kill ";
        game.addLogLine(line);

        Set<String> expectedPlayers = new HashSet<>();
        expectedPlayers.add("Player1");
        expectedPlayers.add("Player2");

        Map<String, Integer> expectedKills = new HashMap<>();
        expectedKills.put("Player1", 1);
        expectedKills.put("Player2", 0);
        Assertions.assertEquals(1, game.getTotalKills());
    }
}