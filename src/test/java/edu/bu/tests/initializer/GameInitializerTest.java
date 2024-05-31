package edu.bu.tests.initializer;

import edu.bu.initializer.GameInitializer;
import edu.bu.initializer.TemplateInitializer;
import edu.bu.model.Room;
import edu.bu.model.entitities.Monster;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameInitializerTest {

    private GameInitializer gameInitializer;
    private final String monsterFilePath = "monster_templates.dat";
    private final String roomFilePath = "room_templates.dat";

    @BeforeEach
    public void setUp() {
        gameInitializer = new GameInitializer();
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
    public void testLoadMonstersWhenFileExists() throws IOException {
        TemplateInitializer templateInitializer = new TemplateInitializer();
        templateInitializer.createAndSaveTemplates();

        List<Monster> monsters = gameInitializer.loadMonsters();

        assertNotNull(monsters, "Monsters should not be null.");
        assertEquals(2, monsters.size(), "There should be 2 monsters.");
        assertTrue(monsters.stream().anyMatch(monster -> "Goblin".equals(monster.getName())), "Goblin should be in the list.");
        assertTrue(monsters.stream().anyMatch(monster -> "Snake".equals(monster.getName())), "Snake should be in the list.");
    }

    @Test
    public void testLoadRoomsWhenFileExists() throws IOException {
        TemplateInitializer templateInitializer = new TemplateInitializer();
        templateInitializer.createAndSaveTemplates();

        List<Room> rooms = gameInitializer.loadRooms();

        assertNotNull(rooms, "Rooms should not be null.");
        assertEquals(2, rooms.size(), "There should be 2 rooms.");
        assertTrue(rooms.stream().anyMatch(room -> "vast courtyard".equals(room.getName())), "Vast courtyard should be in the list.");
        assertTrue(rooms.stream().anyMatch(room -> "small stone room".equals(room.getName())), "Small stone room should be in the list.");
    }

    @Test
    public void testInitializeTemplatesIfNeededCreatesFiles() {
        gameInitializer.initializeTemplatesIfNeeded();

        assertTrue(Files.exists(Paths.get(monsterFilePath)), "Monster templates file should exist.");
        assertTrue(Files.exists(Paths.get(roomFilePath)), "Room templates file should exist.");
    }

    @Test
    public void testInitializeTemplatesIfNeededDoesNotOverwriteExistingFiles() throws IOException, InterruptedException {
        TemplateInitializer templateInitializer = new TemplateInitializer();
        templateInitializer.createAndSaveTemplates();

        long monsterFileTimestampBefore = Files.getLastModifiedTime(Paths.get(monsterFilePath)).toMillis();
        long roomFileTimestampBefore = Files.getLastModifiedTime(Paths.get(roomFilePath)).toMillis();

        // Wait for a brief moment to ensure timestamps will differ if files are overwritten
        Thread.sleep(100);

        gameInitializer.initializeTemplatesIfNeeded();

        long monsterFileTimestampAfter = Files.getLastModifiedTime(Paths.get(monsterFilePath)).toMillis();
        long roomFileTimestampAfter = Files.getLastModifiedTime(Paths.get(roomFilePath)).toMillis();

        assertEquals(monsterFileTimestampBefore, monsterFileTimestampAfter, "Monster templates file should not be overwritten.");
        assertEquals(roomFileTimestampBefore, roomFileTimestampAfter, "Room templates file should not be overwritten.");
    }
}
