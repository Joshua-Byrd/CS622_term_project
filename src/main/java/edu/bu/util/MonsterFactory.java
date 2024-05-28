package edu.bu.util;

import edu.bu.model.Room;
import edu.bu.model.entitities.FacadeEntities;
import edu.bu.model.entitities.Monster;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;

import java.util.Random;

public class MonsterFactory {
    private static final Random random = new Random();
    private static final double SPAWN_PROBABILITY = 0.25; // 25% chance to spawn a monster

    /**
     * INTENT: Attempts to spawn a monster with the given SPAWN_PROBABILITY. For now, there is only a single
     * monsters, but more will be added over time.
     * PRECONDITION: room must not be null.
     * POSTCONDITION: if random number < less than SPAWN_PROBABILITY, a new monster is created and added to the room.
     * @param room
     */
    public static void trySpawnMonster(Room room) {
        if (random.nextDouble() < SPAWN_PROBABILITY) {
            Weapon claw = new Weapon("Claw", "Sharp claws", 1.0, 3, 0);
            Armor hide = new Armor("Hide", "Thick hide", 2.0, 1, 0);
            Monster monster = FacadeEntities.getTheInstance().createMonster("Goblin", "A small, green creature", 3, claw, hide);
            room.addMonster(monster);
        }
    }
}
