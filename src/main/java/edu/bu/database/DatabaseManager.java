package edu.bu.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;

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

            String createFinalStatsTable = "CREATE TABLE IF NOT EXISTS FinalStats (" +
                    "final_stats_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_name TEXT," +
                    "description TEXT," +
                    "max_health INTEGER," +
                    "current_health INTEGER," +
                    "current_room TEXT," +
                    "killed_by_monster TEXT," +
                    "equipped_weapon TEXT," +
                    "equipped_armor TEXT," +
                    "attack_rating INTEGER," +
                    "defense_rating INTEGER," +
                    "gold_held REAL," +
                    "rooms_visited INTEGER," +
                    "monsters_defeated INTEGER," +
                    "inventory TEXT" +
                    ");";

            statement.execute(createPlayersTable);
            statement.execute(createGameSessionsTable);
            statement.execute(createFinalStatsTable);

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

    public void saveFinalStats(Player player, String killedByMonster) {
        String sql = "INSERT INTO FinalStats (player_name, description, max_health, current_health, current_room, " +
                "killed_by_monster, equipped_weapon, equipped_armor, attack_rating, defense_rating, gold_held, " +
                "rooms_visited, monsters_defeated, inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getDescription());
            pstmt.setInt(3, player.getMaxHealth());
            pstmt.setInt(4, player.getCurrentHealth());
            pstmt.setString(5, player.getCurrentRoom().getName());
            pstmt.setString(6, killedByMonster);
            pstmt.setString(7, player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None");
            pstmt.setString(8, player.getEquippedArmor() != null ? player.getEquippedArmor().getName() : "None");
            pstmt.setInt(9, player.getAttackRating());
            pstmt.setInt(10, player.getDefenseRating());
            pstmt.setDouble(11, player.getGoldHeld());
            pstmt.setInt(12, player.getRoomsVisited());
            pstmt.setInt(13, player.getMonstersDefeated());
            pstmt.setString(14, serializeInventory(player.getInventory())); // Serialize inventory to a string or JSON

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String serializeInventory(Inventory<Item> inventory) {
        return inventory.getAllItems().toString();
    }

    public List<String> getPlayerDeathDetails() {
        String sql = "SELECT * FROM FinalStats";
        List<String> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String details = rs.getString("player_name") + " was killed in " + rs.getString("current_room") + " by a " +
                        rs.getString("killed_by_monster") + ". They had " + rs.getDouble("gold_held") + " gold. They were wearing " +
                        rs.getString("equipped_armor") + " and wielding " + rs.getString("equipped_weapon") + ". " +
                        "They visited " + rs.getInt("rooms_visited") + " rooms and defeated " + rs.getInt("monsters_defeated") + " monsters.";
                result.add(details);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }



}