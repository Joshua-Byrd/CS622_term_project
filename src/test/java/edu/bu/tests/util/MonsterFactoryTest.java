package edu.bu.tests.util;
import edu.bu.model.Room;
import edu.bu.model.entitities.Monster;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Weapon;
import edu.bu.util.MonsterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MonsterFactoryTest {

    private List<Monster> monsterTemplates;
    private Room testRoom;
    private Weapon claw;
    private Armor hide;
    private Weapon fang;
    private Armor scales;

    @BeforeEach
    public void setUp() {
        // Create weapons and armors for monsters
        claw = new Weapon("Claw", "Sharp claws", 2.0, 4, 0.0);
        hide = new Armor("Hide", "Thick hide", 10.0, 3, 0.0);
        fang = new Weapon("Fang", "Sharp fangs", 1.5, 3, 0.0);
        scales = new Armor("Scales", "Thick scales", 8.0, 1, 0.0);

        // Create monsters
        Monster goblin = new Monster("Goblin", "A sneaky goblin", 7, claw, hide);
        Monster snake = new Monster("Snake", "A venomous snake", 3, fang, scales);

        monsterTemplates = new ArrayList<>();
        monsterTemplates.add(goblin);
        monsterTemplates.add(snake);

        MonsterFactory.initialize(monsterTemplates);

        // Create a test room
        testRoom = new Room("test room", "A room for testing purposes", new Inventory<>(1000));
    }

    @Test
    public void testInitialize() {
        assertNotNull(monsterTemplates, "Monster templates should not be null after initialization.");
        assertEquals(2, monsterTemplates.size(), "Monster templates should contain two monsters.");
    }

    @Test
    public void testTrySpawnMonster() {
        MonsterFactory.trySpawnMonster(testRoom);

        if (testRoom.getMonsters().isEmpty()) {
            // No monster spawned, which is possible given the 40% spawn probability
            assertTrue(testRoom.getMonsters().isEmpty(), "No monster should be spawned with 60% probability.");
        } else {
            // A monster spawned
            assertEquals(1, testRoom.getMonsters().size(), "One monster should be spawned in the room.");
            Monster spawnedMonster = testRoom.getMonsters().get(0);
            assertTrue(monsterTemplates.stream().anyMatch(template ->
                    template.getName().equals(spawnedMonster.getName())
                            && template.getDescription().equals(spawnedMonster.getDescription())
                            && template.getMaxHealth() == spawnedMonster.getMaxHealth()
                            && template.getEquippedWeapon().equals(spawnedMonster.getEquippedWeapon())
                            && template.getEquippedArmor().equals(spawnedMonster.getEquippedArmor())
            ), "The spawned monster should match one of the templates.");
        }
    }

    @Test
    public void testTrySpawnMonsterEmptyTemplate() {
        // Reinitialize with empty template list
        MonsterFactory.initialize(new ArrayList<>());
        MonsterFactory.trySpawnMonster(testRoom);

        assertTrue(testRoom.getMonsters().isEmpty(), "No monster should be spawned with an empty template list.");
    }
}
