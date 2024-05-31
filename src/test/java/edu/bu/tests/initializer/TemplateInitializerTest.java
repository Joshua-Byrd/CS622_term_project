package edu.bu.tests.initializer;

import edu.bu.initializer.TemplateInitializer;
import edu.bu.model.Room;
import edu.bu.model.entitities.Monster;
import edu.bu.util.TemplateService;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateInitializerTest {

    private TemplateInitializer templateInitializer;
    private final String monsterFilePath = "monster_templates.dat";
    private final String roomFilePath = "room_templates.dat";

    @BeforeEach
    public void setUp() {
        templateInitializer = new TemplateInitializer();
    }

    @AfterEach
    public void tearDown() {
        // Clean up the files after each test
        try {
            Files.deleteIfExists(Paths.get(monsterFilePath));
            Files.deleteIfExists(Paths.get(roomFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAndSaveTemplates() {
        templateInitializer.createAndSaveTemplates();

        // Verify that the monster templates file exists
        assertTrue(Files.exists(Paths.get(monsterFilePath)), "Monster templates file should exist.");

        // Verify that the room templates file exists
        assertTrue(Files.exists(Paths.get(roomFilePath)), "Room templates file should exist.");

        // Load the templates back and verify their contents
        try {
            TemplateService<Monster> monsterTemplateService = new TemplateService<>();
            TemplateService<Room> roomTemplateService = new TemplateService<>();

            List<Monster> monsters = monsterTemplateService.loadTemplates(monsterFilePath);
            List<Room> rooms = roomTemplateService.loadTemplates(roomFilePath);

            // Verify the monsters
            assertNotNull(monsters, "Monsters should not be null.");
            assertEquals(2, monsters.size(), "There should be 2 monsters.");
            assertTrue(monsters.stream().anyMatch(monster -> "Goblin".equals(monster.getName())), "Goblin should be in the list.");
            assertTrue(monsters.stream().anyMatch(monster -> "Snake".equals(monster.getName())), "Snake should be in the list.");

            // Verify the rooms
            assertNotNull(rooms, "Rooms should not be null.");
            assertEquals(2, rooms.size(), "There should be 2 rooms.");
            assertTrue(rooms.stream().anyMatch(room -> "vast courtyard".equals(room.getName())), "Vast courtyard should be in the list.");
            assertTrue(rooms.stream().anyMatch(room -> "small stone room".equals(room.getName())), "Small stone room should be in the list.");

        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown during template loading: " + e.getMessage());
        }
    }
}
