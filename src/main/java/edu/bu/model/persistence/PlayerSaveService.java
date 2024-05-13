package edu.bu.model.persistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.model.entitities.Player;

import java.io.File;
import java.io.IOException;

public class PlayerSaveService {
    private static final String SAVE_FILE_PATH = "player_save.json";

    /**
     * Saves the current state of the player to a JSON file.
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
     * Loads the player state from a JSON file.
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

