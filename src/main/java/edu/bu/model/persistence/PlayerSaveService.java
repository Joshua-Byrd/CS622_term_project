package edu.bu.model.persistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.model.entitities.Player;

import java.io.File;
import java.io.IOException;

/**
 * Provides a way for the player to save their game and resume later.
 */
public class PlayerSaveService {
    private static final String SAVE_FILE_PATH = "player_save.json";

    /**
     * INTENT: Saves the current state of the player to a JSON file, giving the player a way to save and
     * later resume their game.
     * PRECONDITION: player must not be null.
     * POSTCONDITION: player_save.json contains the entire data hierarchy of player in JSON format
     * @param player The player object to save.
     */
    public void save(Player player) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Writing to a file
            mapper.writeValue(new File(SAVE_FILE_PATH), player);
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }

    /**
     * INTENT: Loads the player data from the saved JSON file and returns a player object.
     * PRECONDITON: player_save.json must exist and must contain player data
     * POSTCONDITION: Return value = a player object containing all saved data
     * @return The loaded player object or null if an error occurs.
     */
    public Player load() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Reading from a file
            return mapper.readValue(new File(SAVE_FILE_PATH), Player.class);
        } catch (IOException e) {
            System.err.println("Error loading player data: " + e.getMessage());
            return null;
        }
    }
}

