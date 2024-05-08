package edu.bu.model;

public class Player extends Entity implements Combatant {
    private int health;
    private Room currentRoom; // Reference to the current room

    public Player(String name, String description, int health, Room currentRoom) {
        super(name, description);
        this.health = health;
        this.currentRoom = currentRoom;
    }

    @Override
    public void attack(Entity target) {
        if (target instanceof Combatant) {
            Combatant combatant = (Combatant) target;
            combatant.takeDamage(10); // Example damage value
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }
}
