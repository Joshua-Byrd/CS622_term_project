package edu.bu.util;

import edu.bu.model.Room;
import edu.bu.model.entitities.Monster;


import java.util.List;
import java.util.Random;

/**
 * Factory class for creating and managing monsters in the game.
 * The class allows for the initialization of monster templates and handles
 * the spawning of monsters in rooms based on a set probability.
 */
public class MonsterFactory {
    private static List<Monster> monsterTemplates;
    private static final double SPAWN_PROBABILITY = 0.4; // 40% chance to spawn a monster

    /**
     * INTENT: To initialize the factory with a list of monster templates.
     * PRECONDITION: The templates list must not be null.
     * POSTCONDITION: The monsterTemplates list is initialized with the provided templates.
     *
     * @param someTemplates A list of Monster objects to be used as templates.
     */
    public static void initialize(List<Monster> someTemplates) {
        monsterTemplates = someTemplates;
    }

    /**
     * INTENT: To attempt to spawn a monster in the specified room based on the spawn probability.
     * PRECONDITION 1: The room parameter must not be null.
     * PRECONDITION 2: SPAWN_PROBABILITY must be a valid double
     * POSTCONDITION: A monster is added to the room's list of monsters if the spawn probability condition is met.
     *
     * @param aRoom The Room object where a monster may be spawned.
     */
    public static void trySpawnMonster(Room aRoom) {
        Random random = new Random();

        if (random.nextDouble() < SPAWN_PROBABILITY && !monsterTemplates.isEmpty()) {
            Monster template = monsterTemplates.get(random.nextInt(monsterTemplates.size()));
            Monster monster = new Monster(template.getName(), template.getDescription(), template.getHealth(),
                    template.getEquippedWeapon(), template.getEquippedArmor());
            aRoom.addMonster(monster);
        }
    }
}

