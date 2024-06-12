package edu.bu.tests.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.bu.model.entitities.PlayerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerStatsTest {

    private PlayerStats playerStats;

    @BeforeEach
    void setUp() {
        playerStats = new PlayerStats("TestPlayer", 100.0, 10);
    }

    @Test
    void testGetPlayerName() {
        assertEquals("TestPlayer", playerStats.getPlayerName(), "Player name should match");
    }

    @Test
    void testGetTotalGold() {
        assertEquals(100.0, playerStats.getTotalGold(), "Total gold should match");
    }

    @Test
    void testGetTotalMonstersKilled() {
        assertEquals(10, playerStats.getTotalMonstersKilled(), "Total monsters killed should match");
    }

    @Test
    void testConstructor() {
        PlayerStats newPlayerStats = new PlayerStats("NewPlayer", 200.0, 20);
        assertEquals("NewPlayer", newPlayerStats.getPlayerName(), "Player name should match");
        assertEquals(200.0, newPlayerStats.getTotalGold(), "Total gold should match");
        assertEquals(20, newPlayerStats.getTotalMonstersKilled(), "Total monsters killed should match");
    }
}
