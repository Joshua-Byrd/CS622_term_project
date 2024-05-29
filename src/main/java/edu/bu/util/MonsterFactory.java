package edu.bu.util;

import edu.bu.model.Room;
import edu.bu.model.entitities.Monster;


import java.util.List;
import java.util.Random;

public class MonsterFactory {
    private static List<Monster> monsterTemplates;
    private static final double SPAWN_PROBABILITY = 0.4; // 40% chance to spawn a monster

    public static void initialize(List<Monster> templates) {
        monsterTemplates = templates;
    }

    public static void trySpawnMonster(Room room) {
        Random random = new Random();

        if (random.nextDouble() < SPAWN_PROBABILITY && !monsterTemplates.isEmpty()) {
            Monster template = monsterTemplates.get(random.nextInt(monsterTemplates.size()));
            Monster monster = new Monster(template.getName(), template.getDescription(), template.getHealth(),
                    template.getEquippedWeapon(), template.getEquippedArmor());
            room.addMonster(monster);
        }
    }
}

