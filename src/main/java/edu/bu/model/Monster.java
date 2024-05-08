package edu.bu.model;

public class Monster extends Entity implements Combatant {
    private int health;

    public Monster(String name, String description, int health) {
        super(name, description);
        this.health = health;
    }

    @Override
    public void attack(Entity target) {
        if (target instanceof Combatant) {
            Combatant combatant = (Combatant) target;
            combatant.takeDamage(15); // Example damage value
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }
}