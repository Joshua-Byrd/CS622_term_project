package edu.bu.model.persistence;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.entitities.Player;
import edu.bu.util.FacadeUtil;

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
     * @param aPlayer The player object to save.
     */
    public void save(Player aPlayer) throws PlayerDataException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Writing to a file
            mapper.writeValue(new File(SAVE_FILE_PATH), aPlayer);
        } catch (IOException e) {
            throw new PlayerDataException("PlayerDataException: Error creating save file in PlayerSaveService.save().", e);
        }
    }

    /**
     * INTENT: Loads the player data from the saved JSON file and returns a player object.
     * PRECONDITON: player_save.json must exist and must contain player data
     * POSTCONDITION: Return value = a player object containing all saved data
     * @return The loaded player object or null if an error occurs.
     */
    public Player load() throws PlayerDataException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Reading from a file
            return mapper.readValue(new File(SAVE_FILE_PATH), new TypeReference<Player>() {});
        } catch (IOException e) {
            //exception handled in main
            e.printStackTrace();
            throw new PlayerDataException("PlayerDataException: Error loading save file in PlayerSaveService.load().", e);
        }
    }
}

