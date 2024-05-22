package edu.bu.tests.model.persistence;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.model.entitities.Player;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.items.Inventory;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.model.Room;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerSaveServiceTest {

    private static final String SAVE_FILE_PATH = "player_save.json";
    private PlayerSaveService playerSaveService;
    private Player testPlayer;

    @BeforeEach
    public void setUp() {
        // Clear save file if it exists
        try {
            Files.deleteIfExists(Paths.get(SAVE_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerSaveService = new PlayerSaveService();

        // Set up a test player
        testPlayer = new Player(
                "TestPlayer",
                "A brave adventurer",
                10,
                new Room("Starting Room", "You are standing in the test room.", new Inventory<Item>(50)),
                new Weapon("dagger", "A small dagger", 1.2, 4, 25.0),
                new Armor("Leather armor", "a cuirass made of leather", 4.5, 4, 25.0),
                new Inventory<Item>(50),
                0.0,
                1,
                0
        );
    }

    @AfterEach
    public void tearDown() {
        // Clear save file if it exists
        try {
            Files.deleteIfExists(Paths.get(SAVE_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() throws PlayerDataException, IOException {
        playerSaveService.save(testPlayer);

        // Check if the file exists
        File saveFile = new File(SAVE_FILE_PATH);
        assertTrue(saveFile.exists(), "Save file should be created.");

        // Read the file and check its contents
        ObjectMapper mapper = new ObjectMapper();
        Player loadedPlayer = mapper.readValue(saveFile, Player.class);

        assertNotNull(loadedPlayer, "Loaded player should not be null.");
        assertEquals(testPlayer.getName(), loadedPlayer.getName(), "Player name should match.");
        assertEquals(testPlayer.getDescription(), loadedPlayer.getDescription(), "Player description should match.");
        assertEquals(testPlayer.getHealth(), loadedPlayer.getHealth(), "Player health should match.");
    }

    @Test
    public void testLoad() throws PlayerDataException, IOException {
        // Save the player first
        playerSaveService.save(testPlayer);

        // Load the player
        Player loadedPlayer = playerSaveService.load();

        assertNotNull(loadedPlayer, "Loaded player should not be null.");
        assertEquals(testPlayer.getName(), loadedPlayer.getName(), "Player name should match.");
        assertEquals(testPlayer.getDescription(), loadedPlayer.getDescription(), "Player description should match.");
        assertEquals(testPlayer.getHealth(), loadedPlayer.getHealth(), "Player health should match.");
    }

    @Test
    public void testLoadNoFile() {
        assertThrows(PlayerDataException.class, () -> {
            playerSaveService.load();
        }, "Loading without a save file should throw PlayerDataException.");
    }
}
