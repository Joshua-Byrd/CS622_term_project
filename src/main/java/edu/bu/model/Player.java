package edu.bu.model;

import edu.bu.util.Die;

import java.util.ArrayList;

public class Player extends Entity implements Combatant {
    private int health;
    private Room currentRoom;
    private int carryingCapacity = 50; //arbitrary number to be tweaked later
    private int currentWeight;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;
    private ArrayList<Item> inventory;

    public Player(String name, String description, int health, Room currentRoom,
                  Weapon weapon, Armor armor) {
        super(name, description);
        this.health = health;
        this.currentRoom = currentRoom;
        this.equippedWeapon = weapon;
        this.equippedArmor = armor;
        this.attackRating = weapon.getAttackRating();
        this.defenseRating = armor.getDefenseRating();
        this.inventory = new ArrayList<>();
        this.inventory.add(weapon);
        this. inventory.add(armor);
        this.currentWeight = updateCurrentWeight();
    }

    @Override
    public void attack(Entity target) {
            Monster monster = (Monster) target;
            Die attackDie = new Die(attackRating);
            //roll for attack and notify player of result
            if (attackDie.rollDie() > monster.getDefenseRating()) {
                System.out.println("You successfully attack with your " +
                        equippedWeapon.getName() + "!");
                monster.takeDamage(attackDie.rollDie());
            } else {
                System.out.println("You missed!");
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

    public int updateCurrentWeight() {
        int weight = 0;
        for (Item item: this.inventory) {
            weight += item.getWeight();
        }
        return weight;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        this.defenseRating = defenseRating;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
