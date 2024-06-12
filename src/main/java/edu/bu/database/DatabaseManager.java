package edu.bu.database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;

/**
 * Manages access to the database, providing methods to create it and all tables if they do not exist, and to save
 * and return player data.
 */
public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:game_database.db";

    public DatabaseManager() {
        initializeDatabase();
    }

    /**
     * INTENT: To initialize the database by creating necessary tables if they do not exist.
     * PRECONDITION: None.
     * POSTCONDITION: The players, game_sessions, and FinalStats tables are created if they do not exist.
     */
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
                    + "start_time TEXT,"
                    + "end_time TEXT,"
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

    /**
     * INTENT: To save a game session's statistics to the database.
     * PRECONDITION 1: The playerId, startTime, endTime, actionsTaken, monstersDefeated, and goldCollected parameters must be valid.
     * PRECONDITION 2: The database must exist and accessible
     * POSTCONDITION: A new record is inserted into the game_sessions table with the provided data.
     *
     * @param playerId The ID of the player.
     * @param startTime The start time of the game session.
     * @param endTime The end time of the game session.
     * @param actionsTaken The number of actions taken during the session.
     * @param monstersDefeated The number of monsters defeated during the session.
     * @param goldCollected The amount of gold collected during the session.
     */
    public void saveGameSession(int playerId, LocalDateTime startTime, LocalDateTime endTime, int actionsTaken, int monstersDefeated, double goldCollected) {
        String insertSessionSQL = "INSERT INTO game_sessions(player_id, start_time, end_time, actions_taken, monsters_defeated, gold_collected) VALUES(?,?,?,?,?,?)";

        String startTimeFormatted = formatLocalDateTime(startTime);
        String endTimeFormatted = formatLocalDateTime(endTime);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSessionSQL)) {

            preparedStatement.setInt(1, playerId);
            preparedStatement.setString (2, startTimeFormatted);
            preparedStatement.setString(3, endTimeFormatted);
            preparedStatement.setInt(4, actionsTaken);
            preparedStatement.setInt(5, monstersDefeated);
            preparedStatement.setDouble(6, goldCollected);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }


    /**
     * INTENT: To save a player's data to the database.
     * PRECONDITION 1: The player object must be valid.
     * PRECONDITION 2: The database must be valid and accessible
     * POSTCONDITION: A new record is inserted into the players table with the player's data, and the player's
     * ID is updated with the generated ID.
     *
     * @param player The player object to be saved.
     * @return The generated ID of the saved player.
     */
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

    /**
     * INTENT: To retrieve the top 5 players by the total amount of gold collected across all sessions.
     * PRECONDITION: The database must exist and be accessible
     * POSTCONDITION: A list of PlayerStats objects representing the top 5 players by gold collected is returned.
     *
     * @return A list of PlayerStats objects.
     */
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

    /**
     * INTENT: To retrieve the top 5 players by the total number of monsters killed across all sessions.
     * PRECONDITION: The database must exist and be accessible
     * POSTCONDITION: A list of PlayerStats objects representing the top 5 players by monsters killed is returned.
     *
     * @return A list of PlayerStats objects.
     */
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

    /**
     * INTENT: To save a player's final statistics upon death to the database.
     * PRECONDITION 1: The player object and killedByMonster string must be valid.
     * PRECONDITION 2: The database must exist and be valid.
     * POSTCONDITION: A new record is inserted into the FinalStats table with the player's final statistics.
     *
     * @param player The player object to be saved.
     * @param killedByMonster The monster that killed the player.
     */
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

    /**
     * INTENT: To return the given inventory as a string for storing in the database
     * PRECONDITION: inventory must not be null
     * POSTCONDITION: return value == the string version of the given inventory
     * @param inventory the inventory to return
     */
    private String serializeInventory(Inventory<Item> inventory) {
        return inventory.getAllItems().toString();
    }

//    /**
//     * INTENT: Accesses the FinalStats table in the database, and returns all rows as strings suitable for printing
//     * PRECONDITION: The database must exist and be valid.
//     * POSTCONDITION: Return value == a list of all rows in the FinalStats table in printable string form.
//     *
//     */
//    public List<String> getPlayerDeathDetails() {
//        String sql = "SELECT * FROM FinalStats";
//        List<String> result = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                String details = rs.getString("player_name") + " was killed in " + rs.getString("current_room") + " by a " +
//                        rs.getString("killed_by_monster") + ". They had " + rs.getDouble("gold_held") + " gold. They were wearing " +
//                        rs.getString("equipped_armor") + " and wielding a " + rs.getString("equipped_weapon") + ".\n" +
//                        "     They visited " + rs.getInt("rooms_visited") + " rooms and defeated " + rs.getInt("monsters_defeated") + " monsters.";
//                result.add(details);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }
    /**
     * INTENT: Accesses the FinalStats table in the database, and returns all rows as strings suitable for printing
     * PRECONDITION: The database must exist and be valid.
     * POSTCONDITION: Return value == a list of all rows in the FinalStats table in printable string form.
     *
     */
    public List<String> getPlayerDeathDetails() {
        String sql = "SELECT * FROM FinalStats";
        List<String> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String playerName = rs.getString("player_name");
                String details = playerName + " was killed in " + rs.getString("current_room") + " by a " +
                        rs.getString("killed_by_monster") + ". They had " + rs.getDouble("gold_held") + " gold. They were wearing " +
                        rs.getString("equipped_armor") + " and wielding a " + rs.getString("equipped_weapon") + ".\n" +
                        "     They visited " + rs.getInt("rooms_visited") + " rooms and defeated " + rs.getInt("monsters_defeated") + " monsters.";

                double avgSessionLength = getAverageSessionLength(playerName);
//                int avgHours = (int) (avgSessionLength / 60);
//                int avgMinutes = (int) (avgSessionLength % 60);
//                String avgSessionLengthFormatted = String.format(" Average session length: %d hours and %d minutes", avgHours, avgMinutes);
                details += String.format(" Average session length: %.2f minutes", avgSessionLength);

                result.add(details);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * INTENT: To calculate the average session length for a given player.
     * PRECONDITION: The player's name must be valid and the database must exist and be accessible.
     * POSTCONDITION: Return value == the average session length in minutes for the given player.
     *
     * @param playerName The name of the player.
     * @return The average session length in minutes.
     */
    private double getAverageSessionLength(String playerName) {
        String sql = "SELECT AVG((julianday(end_time) - julianday(start_time)) * 24 * 60) AS avg_session_length_minutes " +
                "FROM game_sessions gs " +
                "JOIN players p ON gs.player_id = p.id " +
                "WHERE p.name = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("avg_session_length_minutes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}