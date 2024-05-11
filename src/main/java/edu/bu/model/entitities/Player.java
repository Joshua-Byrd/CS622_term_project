package edu.bu.model.entitities;

import edu.bu.controller.GameController;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.Room;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;
import edu.bu.util.MessageService;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Represents the player character
 */
public class Player extends Entity implements Combatant {
    private int health;
    private Room currentRoom;
    private double carryingCapacity = 50; // Arbitrary number to be tweaked later
    private double currentWeight;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;

    public Player(String aName, String aDescription, int aHealth, Room aCurrentRoom,
                  Weapon aWeapon, Armor aArmor, ArrayList<Item> anInventory) {
        super(aName, aDescription, anInventory);
        this.health = aHealth;
        this.currentRoom = aCurrentRoom;
        this.equippedWeapon = aWeapon;
        this.equippedArmor = aArmor;
        this.attackRating = aWeapon.getAttackRating();
        this.defenseRating = aArmor.getDefenseRating();
        this.currentWeight = updateCurrentWeight(anInventory);
    }

    /**
     * INTENT: To allow the player to engage in combat with a monster, potentially dealing damage based on a successful hit check.
     * PRECONDITION: The target must not be null and must be an instance of Monster.
     * POSTCONDITION 1: If the attack hits, the target's health is reduced by the damage dealt.
     * POSTCONDITION 2: If the attack hits, Messages about the attack outcome are sent to the view.
     *
     * @param aTarget The entity that is the target of the attack.
     */
    @Override
    public void attack(Entity aTarget) {
        if (aTarget instanceof Monster) {
            MessageService.sendMessage("You attack with your " + getEquippedWeapon().getName() + "!");
            Monster monster = (Monster) aTarget;

            if (isHit(monster)) {
                Die die = new Die(this.getAttackRating());
                int damageTaken = die.rollDie();
                monster.takeDamage(damageTaken);
                MessageService.sendMessage("You hit for " + damageTaken + " damage!");
            } else {
                MessageService.sendMessage("You miss!");
            }
        }
    }

    /**
     * INTENT: To simulate the effect of taking damage during combat, reducing the player's health accordingly.
     * PRECONDITION: The damage amount must be a non-negative integer.
     * POSTCONDITION: The player's health is decreased by the amount of damage taken.
     *
     * @param aDamage The amount of damage to be applied to the player's health.
     */
    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
    }

    /**
     * INTENT: Updates the players current weight by iterating through their inventory and summing the weights of their Items
     * PRECONDITION: anInventory must not be null.
     * POSTCONDITION: return value == sum of the weights of all items in anInventory
     *
     * @param anInventory an arraylist of items to sum
     * @return the sum of the item weights in the given arraylist
     */
    public double updateCurrentWeight(ArrayList<Item> anInventory) {
        if (anInventory.isEmpty()) {
            return 0;
        }

        double weight = 0;
        for (Item item : anInventory) {
            weight += item.getWeight();
        }
        return weight;
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

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double aCurrentWeight) {
        this.currentWeight = aCurrentWeight;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double aCarryingCapacity) {
        this.carryingCapacity = aCarryingCapacity;
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
}

