package edu.bu.model.entitities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.bu.controller.GameController;
import edu.bu.model.items.*;
import edu.bu.model.Room;
import edu.bu.util.Die;
import edu.bu.util.FacadeUtil;
import edu.bu.util.MessageService;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Represents the player character
 */
public class Player extends Entity implements Combatant {
    private int health;
    private Room currentRoom;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;
    private Inventory<Item> inventory;
    private double goldHeld;
    private int roomsVisited;
    private int monstersDefeated;
    private final FacadeUtil facadeUtil = FacadeUtil.getTheInstance();

    @JsonCreator
    public Player(@JsonProperty("name") String aName,
                  @JsonProperty("description") String aDescription,
                  @JsonProperty("health") int aHealth,
                  @JsonProperty("currentRoom") Room aCurrentRoom,
                  @JsonProperty("equippedWeapon") Weapon aWeapon,
                  @JsonProperty("equippedArmor") Armor aArmor,
                  @JsonProperty("inventory") Inventory<Item> anInventory,
                  @JsonProperty("goldHeld") double someGold,
                  @JsonProperty("roomsVisited") int someRooms,
                  @JsonProperty("monstersDefeated") int someMonsters) {
        super(aName, aDescription, anInventory);
        this.health = aHealth;
        this.currentRoom = aCurrentRoom;
        this.equippedWeapon = aWeapon;
        this.equippedArmor = aArmor;
        this.attackRating = aWeapon.getAttackRating();
        this.defenseRating = aArmor.getDefenseRating();
        this.inventory = anInventory;
        this.goldHeld = someGold;
        this.roomsVisited = someRooms;
        this.monstersDefeated = someMonsters;
    }

    /**
     * INTENT: To allow the player to engage in combat with a monster, potentially dealing damage based on a successful hit check.
     * PRECONDITION: The target must not be null and must be an instance of Monster.
     * POSTCONDITION 1: If attack is successful, target health -= damageTaken
     * POSTCONDITION 2: Feedback message is displayed to the screen
     *
     * @param aTarget The entity that is the target of the attack.
     */
    @Override
    public void attack(Entity aTarget) {
        if (aTarget instanceof Monster) {
            MessageService.sendMessage("You attack with your " + getEquippedWeapon().getName() + "!");
            Monster monster = (Monster) aTarget;

            if (isHit(monster)) {
                int damageTaken = new Die(attackRating).rollDie();
                monster.takeDamage(damageTaken);
                facadeUtil.sendMessage("You hit for " + damageTaken + " damage!");
                if (!monster.isAlive()) {
                    facadeUtil.sendMessage("You have defeated the monster!");
                }
            } else {
                facadeUtil.sendMessage("You miss!");
            }
        }
    }

    /**
     * INTENT: To simulate the effect of taking damage during combat, reducing the player's health accordingly.
     * PRECONDITION: The damage amount must be a non-negative integer.
     * POSTCONDITION: health -= aDamage
     *
     * @param aDamage The amount of damage to be applied to the player's health.
     */
    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
    }

    /**
     * INTENT: To add an item to a player's inventory. First checks to see if the given item is a Tradeable
     * item (non-tradeable items include chests and other items that a player cannot carry), then adds it to the
     * player's inventory.
     * PRECONDITION: The given item must implement the Tradeable interface, and there must be room in the
     * inventory.
     * POSTCONDITION 1: The given item is added to the inventory
     * POSTCONDITION 2: An IllegalArgumentException is thrown.
     * @param anItem an item to be added.
     * @throws IllegalArgumentException if item is not Tradeable
     */
    public void addItemToInventory(Item anItem) throws IllegalArgumentException{
        if (anItem instanceof Tradeable && inventory.canAddItem(anItem)) {
            inventory.addItem(anItem);
        } else {
            throw new IllegalArgumentException("Only Tradeable items can be added to the player's inventory");
        }
    }

    //Getter and Setter methods
    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int aHealth) {
        this.health = aHealth;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room aNewRoom) {
        this.currentRoom = aNewRoom;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }

    @JsonIgnore
    public double getCurrentWeight() {
        return inventory.getTotalWeight();
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon anEquippedWeapon) {
        this.equippedWeapon = anEquippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor anEquippedArmor) {
        this.equippedArmor = anEquippedArmor;
    }

    @Override
    public Inventory<Item> getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(Inventory<Item> inventory) {
        this.inventory = inventory;
    }

    public double getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(double goldHeld) {
        this.goldHeld = goldHeld;
    }

    public int getRoomsVisited() {
        return roomsVisited;
    }

    public void setRoomsVisited(int roomsVisited) {
        this.roomsVisited = roomsVisited;
    }

    public int getMonstersDefeated() {
        return monstersDefeated;
    }

    public void setMonstersDefeated(int monstersDefeated) {
        this.monstersDefeated = monstersDefeated;
    }
}

