package edu.bu.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:game_database.db";

    public DatabaseManager() {
        initializeDatabase();
    }

    public void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            String createPlayersTable = "CREATE TABLE IF NOT EXISTS players ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "description TEXT,"
                    + "maxHealth INTEGER,"
                    + "currentHealth INTEGER,"
                    + "goldHeld REAL,"
                    + "roomsVisited INTEGER,"
                    + "monstersDefeated INTEGER"
                    + ");";

            String createGameSessionsTable = "CREATE TABLE IF NOT EXISTS game_sessions ("
                    + "session_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "player_id INTEGER,"
                    + "start_time TIMESTAMP,"
                    + "end_time TIMESTAMP,"
                    + "actions_taken INTEGER,"
                    + "monsters_defeated INTEGER,"
                    + "gold_collected REAL,"
                    + "FOREIGN KEY(player_id) REFERENCES players(id)"
                    + ");";

            statement.execute(createPlayersTable);
            statement.execute(createGameSessionsTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGameSession(int playerId, Timestamp startTime, Timestamp endTime, int actionsTaken, int monstersDefeated, double goldCollected) {
        String insertSessionSQL = "INSERT INTO game_sessions(player_id, start_time, end_time, actions_taken, monsters_defeated, gold_collected) VALUES(?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSessionSQL)) {

            preparedStatement.setInt(1, playerId);
            preparedStatement.setTimestamp(2, startTime);
            preparedStatement.setTimestamp(3, endTime);
            preparedStatement.setInt(4, actionsTaken);
            preparedStatement.setInt(5, monstersDefeated);
            preparedStatement.setDouble(6, goldCollected);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int savePlayer(Player player) {
        String insertPlayerSQL = "INSERT INTO players(name, description, maxHealth, currentHealth, goldHeld, " +
                "roomsVisited, monstersDefeated) VALUES(?,?,?,?,?,?,?)";
        String selectPlayerIdSQL = "SELECT last_insert_rowid() AS id";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertPlayerSQL)) {

            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getDescription());
            preparedStatement.setInt(3, player.getMaxHealth());
            preparedStatement.setInt(4, player.getCurrentHealth());
            preparedStatement.setDouble(5, player.getGoldHeld());
            preparedStatement.setInt(6, player.getRoomsVisited());
            preparedStatement.setInt(7, player.getMonstersDefeated());

            preparedStatement.executeUpdate();

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectPlayerIdSQL)) {
                if (resultSet.next()) {
                    int playerId = resultSet.getInt("id");
                    player.setId(playerId);
                    return playerId;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<PlayerStats> getTopPlayersByGold() {
        String query = "SELECT p.name, SUM(gs.gold_collected) AS total_gold "
                + "FROM players p "
                + "JOIN game_sessions gs ON p.id = gs.player_id "
                + "GROUP BY p.id "
                + "ORDER BY total_gold DESC "
                + "LIMIT 5";
        List<PlayerStats> players = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String playerName = resultSet.getString("name");
                double totalGold = resultSet.getDouble("total_gold");
                players.add(new PlayerStats(playerName, totalGold, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<PlayerStats> getTopPlayersByMonstersKilled() {
        String query = "SELECT p.name, SUM(gs.monsters_defeated) AS total_monsters_killed "
                + "FROM players p "
                + "JOIN game_sessions gs ON p.id = gs.player_id "
                + "GROUP BY p.id "
                + "ORDER BY total_monsters_killed DESC "
                + "LIMIT 5";
        List<PlayerStats> players = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String playerName = resultSet.getString("name");
                int totalMonstersKilled = resultSet.getInt("total_monsters_killed");
                players.add(new PlayerStats(playerName, 0, totalMonstersKilled));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

}