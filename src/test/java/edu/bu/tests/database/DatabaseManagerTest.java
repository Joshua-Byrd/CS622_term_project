package edu.bu.tests.database;

import edu.bu.database.DatabaseManager;
import edu.bu.model.Room;
import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Weapon;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseManagerTest {

    private static DatabaseManager databaseManager;
    Weapon weapon = new Weapon("test_weapon", "a weapon for testing", 0,0,0);
    Armor armor = new Armor("test_armor", "an armor for testing", 0, 0, 0);

    @BeforeAll
    static void setup() {
        databaseManager = new DatabaseManager();
        databaseManager.initializeDatabase();
    }

    @BeforeEach
    void clearDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:game_database.db");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM game_sessions");
            statement.executeUpdate("DELETE FROM players");
            statement.executeUpdate("DELETE FROM FinalStats");
        }
    }

    @Test
    void testInitializeDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:game_database.db");
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='players'");
            assertTrue(rs.next(), "Players table should exist");

            rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='game_sessions'");
            assertTrue(rs.next(), "Game sessions table should exist");

            rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='FinalStats'");
            assertTrue(rs.next(), "FinalStats table should exist");

        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testSavePlayer() {
        Player player = new Player(0, "TestPlayer", "A brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        int playerId = databaseManager.savePlayer(player);
        assertTrue(playerId > 0, "Player ID should be greater than 0");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:game_database.db");
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM players WHERE id = " + playerId);
            assertTrue(rs.next(), "Player should be saved in the database");
            assertEquals("TestPlayer", rs.getString("name"), "Player name should match");
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testSaveGameSession() {
        Player player = new Player(0, "TestPlayer", "A brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        int playerId = databaseManager.savePlayer(player);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusSeconds(1000);
        databaseManager.saveGameSession(playerId, startTime, endTime, 10, 5, 50.0);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:game_database.db");
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM game_sessions WHERE player_id = " + playerId);
            assertTrue(rs.next(), "Game session should be saved in the database");
            assertEquals(10, rs.getInt("actions_taken"), "Actions taken should match");
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testGetTopPlayersByGold() {
        Player player1 = new Player(0, "Player1", "A brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        Player player2 = new Player(0, "Player2", "Another brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        int player1Id = databaseManager.savePlayer(player1);
        int player2Id = databaseManager.savePlayer(player2);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusSeconds(1000);
        databaseManager.saveGameSession(player1Id, startTime, endTime, 10, 5, 100.0);
        databaseManager.saveGameSession(player2Id, startTime, endTime, 10, 5, 200.0);

        List<PlayerStats> topPlayers = databaseManager.getTopPlayersByGold();
        assertEquals(2, topPlayers.size(), "There should be 2 players");

        assertEquals("Player2", topPlayers.get(0).getPlayerName(), "Top player should be Player2");
        assertEquals(200.0, topPlayers.get(0).getTotalGold(), "Player2 gold should be 200.0");
    }

    @Test
    void testGetTopPlayersByMonstersKilled() {
        Player player1 = new Player(0, "Player1", "A brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        Player player2 = new Player(0, "Player2", "Another brave adventurer", 100, null, weapon, armor, null, 0.0, 0, 0);
        int player1Id = databaseManager.savePlayer(player1);
        int player2Id = databaseManager.savePlayer(player2);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusSeconds(1000);
        databaseManager.saveGameSession(player1Id, startTime, endTime, 10, 10, 100.0);
        databaseManager.saveGameSession(player2Id, startTime, endTime, 10, 20, 200.0);

        List<PlayerStats> topPlayers = databaseManager.getTopPlayersByMonstersKilled();
        assertEquals(2, topPlayers.size(), "There should be 2 players");

        assertEquals("Player2", topPlayers.get(0).getPlayerName(), "Top player should be Player2");
        assertEquals(20, topPlayers.get(0).getTotalMonstersKilled(), "Player2 monsters killed should be 20");
    }

    @Test
    void testSaveFinalStats() {
        Player player = new Player(0, "TestPlayer", "A brave adventurer", 100, null, weapon, armor, new Inventory<>(0), 0.0, 0, 0);
        player.setCurrentRoom(new Room("TestRoom", "A mysterious room", null, 0));
        player.setEquippedWeapon(new Weapon("Sword", "A sharp blade", 5.0, 10, 50.0));
        player.setEquippedArmor(new Armor("Shield", "A sturdy shield", 7.0, 5, 70.0));

        databaseManager.saveFinalStats(player, "Goblin");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:game_database.db");
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM FinalStats WHERE player_name = 'TestPlayer'");
            assertTrue(rs.next(), "Final stats should be saved in the database");
            assertEquals("Goblin", rs.getString("killed_by_monster"), "Killed by monster should match");
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testGetPlayerDeathDetails() {
        Player player = new Player(0,"TestPlayer", "A brave adventurer", 100, null, weapon, armor, new Inventory<>(0), 0.0, 0, 0);
        player.setCurrentRoom(new Room("TestRoom", "A mysterious room", null, 0));
        player.setEquippedWeapon(new Weapon("Sword", "A sharp blade", 5.0, 10, 50.0));
        player.setEquippedArmor(new Armor("Shield", "A sturdy shield", 7.0, 5, 70.0));

        databaseManager.saveFinalStats(player, "Goblin");

        List<String> deathDetails = databaseManager.getPlayerDeathDetails();
        assertFalse(deathDetails.isEmpty(), "Death details should not be empty");

        String expectedDetails = "TestPlayer was killed in TestRoom by a Goblin. They had 0.0 gold. They were wearing Shield and wielding a Sword.\n     They visited 0 rooms and defeated 0 monsters. Average session length: 0 hours and 0 minutes";
        assertTrue(deathDetails.contains(expectedDetails), "Death details should contain the expected string");
    }
}
