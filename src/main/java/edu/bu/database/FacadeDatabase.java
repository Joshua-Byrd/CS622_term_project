package edu.bu.database;


import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Facade for the database package, providing a singleton access point to the DatabaseManager functionality.
 */
public class FacadeDatabase {

    private static FacadeDatabase instance;
    private final DatabaseManager databaseManager;

    // Private constructor to prevent instantiation
    private FacadeDatabase() {
        this.databaseManager = new DatabaseManager();
    }

    /**
     * INTENT: To provide a global point of access to the FacadeDatabase singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeDatabase.
     *
     * @return The singleton instance of FacadeDatabase.
     */
    public static synchronized FacadeDatabase getTheInstance() {
        if (instance == null) {
            instance = new FacadeDatabase();
        }
        return instance;
    }

    /**
     * INTENT: To save a game session's statistics.
     * PRECONDITION: The parameters must be valid.
     * POSTCONDITION: The session statistics are saved to the database.
     *
     * @param playerId The ID of the player.
     * @param startTime The start time of the game session.
     * @param endTime The end time of the game session.
     * @param actionsTaken The number of actions taken during the session.
     * @param monstersDefeated The number of monsters defeated during the session.
     * @param goldCollected The amount of gold collected during the session.
     */
    public void saveGameSession(int playerId, LocalDateTime startTime, LocalDateTime endTime, int actionsTaken, int monstersDefeated, double goldCollected) {
        databaseManager.saveGameSession(playerId, startTime, endTime, actionsTaken, monstersDefeated, goldCollected);
    }

    /**
     * INTENT: To save a player's data.
     * PRECONDITION: The player object must be valid.
     * POSTCONDITION: The player's data is saved to the database.
     *
     * @param player The player object to be saved.
     * @return The generated ID of the saved player.
     */
    public int savePlayer(Player player) {
        return databaseManager.savePlayer(player);
    }

    /**
     * INTENT: To retrieve the top 5 players by the total amount of gold collected.
     * PRECONDITION: The database must exist and be accessible.
     * POSTCONDITION: A list of PlayerStats objects representing the top 5 players by gold collected is returned.
     *
     * @return A list of PlayerStats objects.
     */
    public List<PlayerStats> getTopPlayersByGold() {
        return databaseManager.getTopPlayersByGold();
    }

    /**
     * INTENT: To retrieve the top 5 players by the total number of monsters killed.
     * PRECONDITION: The database must exist and be accessible.
     * POSTCONDITION: A list of PlayerStats objects representing the top 5 players by monsters killed is returned.
     *
     * @return A list of PlayerStats objects.
     */
    public List<PlayerStats> getTopPlayersByMonstersKilled() {
        return databaseManager.getTopPlayersByMonstersKilled();
    }

    /**
     * INTENT: To save a player's final statistics upon death.
     * PRECONDITION: The player object and killedByMonster string must be valid.
     * POSTCONDITION: The player's final statistics are saved to the database.
     *
     * @param player The player object to be saved.
     * @param killedByMonster The monster that killed the player.
     */
    public void saveFinalStats(Player player, String killedByMonster) {
        databaseManager.saveFinalStats(player, killedByMonster);
    }

    /**
     * INTENT: To retrieve player death details.
     * PRECONDITION: The database must exist and be accessible.
     * POSTCONDITION: A list of strings representing player death details is returned.
     *
     * @return A list of strings representing player death details.
     */
    public List<String> getPlayerDeathDetails() {
        return databaseManager.getPlayerDeathDetails();
    }
}
