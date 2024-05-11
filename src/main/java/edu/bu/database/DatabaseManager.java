package edu.bu.database;

import edu.bu.model.entitities.Player;

import java.sql.*;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager(String url) {
        try {
            conn = DriverManager.getConnection(url);
            initializeDatabase();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
        }
    }

    private void initializeDatabase() {
        // Create tables if they do not exist
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS player_stats (id INTEGER PRIMARY KEY, gold INTEGER, rooms_visited INTEGER, monsters_defeated INTEGER)");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void savePlayerStats(Player player) {
        // Implement saving logic
    }

}
