package edu.bu.model.entitities;

import edu.bu.model.Room;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;

/**
 * Facade for the entities package, providing a singleton access point to the entities functionalities.
 */
public class FacadeEntities {

    private static FacadeEntities instance;


    // Private constructor to prevent instantiation
    private FacadeEntities() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeEntities singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeEntities.
     *
     * @return The singleton instance of FacadeEntities.
     */
    public static synchronized FacadeEntities getTheInstance() {
        if (instance == null) {
            instance = new FacadeEntities();
        }
        return instance;
    }

    // Player Methods
    public Player createPlayer(int id, String name, String description, int health, Room currentRoom,
                               Weapon weapon, Armor armor, Inventory<Item> inventory,
                               double goldHeld, int roomsVisited, int monstersDefeated) {
        return new Player(id, name, description, health, currentRoom, weapon, armor, inventory, goldHeld, roomsVisited, monstersDefeated);
    }

    public int getPlayerHealth(Player player) {
        return player.getMaxHealth();
    }

    public void setPlayerHealth(Player player, int health) {
        player.setMaxHealth(health);
    }

    public Room getPlayerCurrentRoom(Player player) {
        return player.getCurrentRoom();
    }

    public void setPlayerCurrentRoom(Player player, Room room) {
        player.setCurrentRoom(room);
    }

    public double getPlayerCurrentWeight(Player player) {
        return player.getCurrentWeight();
    }

    public Weapon getPlayerEquippedWeapon(Player player) {
        return player.getEquippedWeapon();
    }

    public void setPlayerEquippedWeapon(Player player, Weapon weapon) {
        player.setEquippedWeapon(weapon);
    }

    public Armor getPlayerEquippedArmor(Player player) {
        return player.getEquippedArmor();
    }

    public void setPlayerEquippedArmor(Player player, Armor armor) {
        player.setEquippedArmor(armor);
    }

    public int getPlayerAttackRating(Player player) {
        return player.getAttackRating();
    }

    public void setPlayerAttackRating(Player player, int attackRating) {
        player.setAttackRating(attackRating);
    }

    public int getPlayerDefenseRating(Player player) {
        return player.getDefenseRating();
    }

    public void setPlayerDefenseRating(Player player, int defenseRating) {
        player.setDefenseRating(defenseRating);
    }

    public double getPlayerGoldHeld(Player player) {
        return player.getGoldHeld();
    }

    public void setPlayerGoldHeld(Player player, double goldHeld) {
        player.setGoldHeld(goldHeld);
    }

    public int getPlayerRoomsVisited(Player player) {
        return player.getRoomsVisited();
    }

    public void setPlayerRoomsVisited(Player player, int roomsVisited) {
        player.setRoomsVisited(roomsVisited);
    }

    public int getPlayerMonstersDefeated(Player player) {
        return player.getMonstersDefeated();
    }

    public void setPlayerMonstersDefeated(Player player, int monstersDefeated) {
        player.setMonstersDefeated(monstersDefeated);
    }

    public void playerAttack(Player player, Entity target) {
        player.attack(target);
    }

    public void playerTakeDamage(Player player, int damage) {
        player.takeDamage(damage);
    }

    // Monster Methods
    public Monster createMonster(String name, String description, int health, Weapon weapon, Armor armor) {
        return new Monster(name, description, health, weapon, armor);
    }

    public int getMonsterHealth(Monster monster) {
        return monster.getMaxHealth();
    }

    public void setMonsterHealth(Monster monster, int health) {
        monster.setMaxHealth(health);
    }

    public Weapon getMonsterEquippedWeapon(Monster monster) {
        return monster.getEquippedWeapon();
    }

    public void setMonsterEquippedWeapon(Monster monster, Weapon weapon) {
        monster.setEquippedWeapon(weapon);
    }

    public Armor getMonsterEquippedArmor(Monster monster) {
        return monster.getEquippedArmor();
    }

    public void setMonsterEquippedArmor(Monster monster, Armor armor) {
        monster.setEquippedArmor(armor);
    }

    public int getMonsterAttackRating(Monster monster) {
        return monster.getAttackRating();
    }

    public void setMonsterAttackRating(Monster monster, int attackRating) {
        monster.setAttackRating(attackRating);
    }

    public int getMonsterDefenseRating(Monster monster) {
        return monster.getDefenseRating();
    }

    public void setMonsterDefenseRating(Monster monster, int defenseRating) {
        monster.setDefenseRating(defenseRating);
    }

    public boolean isMonsterAlive(Monster monster) {
        return monster.isAlive();
    }

    public void setMonsterAlive(Monster monster, boolean isAlive) {
        monster.setAlive(isAlive);
    }

    public void monsterAttack(Monster monster, Entity target) {
        monster.attack(target);
    }

    public void monsterTakeDamage(Monster monster, int damage) {
        monster.takeDamage(damage);
    }

    // Merchant Methods
    public Merchant createMerchant(String name, String description, Inventory<Item> inventory) {
        return new Merchant(name, description, inventory);
    }

    public void merchantTrade(Merchant merchant) {
        merchant.trade();
    }
}

