package edu.bu.model.entitities;

import edu.bu.controller.GameController;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;
import edu.bu.util.MessageService;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Represents a Monster entity capable of combat within the game.
 * Monsters can engage in attacks against players and take damage.
 */
public class Monster extends Entity implements Combatant {
    private int health;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;

    public Monster(String aName, String aDescription, int aHealth, Weapon aWeapon, Armor aArmor, ArrayList<Item> anInventory) {
        super(aName, aDescription, anInventory);
        this.health = aHealth;
        this.equippedWeapon = aWeapon;
        this.equippedArmor = aArmor;
        this.attackRating = aWeapon.getAttackRating();
        this.defenseRating = aArmor.getDefenseRating();
    }

    /**
     * INTENT: To perform an attack using the Monster's equipped weapon, potentially dealing damage.
     * PRECONDITION: The target must be an instance of Player and not null.
     * POSTCONDITION 1: If the attack hits, the target's health is reduced by the damage amount.
     * POSTCONDITION 2: If the attack hits, feedback to the player is sent to the View
     *
     * @param aTarget The entity that is the target of the attack.
     */
    @Override
    public void attack(Entity aTarget) {
        if (aTarget instanceof Player) {
            MessageService.sendMessage("The monster attacks with its " + getEquippedWeapon().getName() + "!");
            Player player = (Player)aTarget;
            if (isHit(player)) {
                Die die = new Die(this.getAttackRating());
                int damageTaken = die.rollDie();
                player.takeDamage(damageTaken);
                MessageService.sendMessage("You are hit for " + damageTaken + " damage!");
            } else {
                MessageService.sendMessage("It misses!");
            }
        }
    }

    /**
     * INTENT: To decrease the Monster's health by a specified damage amount following an attack.
     * PRECONDITION: The damage amount must be a non-negative integer.
     * POSTCONDITION: The Monster's health is decreased by the specified amount, possibly resulting in the Monster's death if health reaches zero.
     *
     * @param aDamage The amount of damage to be applied to the Monster's health.
     */
    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
    }


    //Getter and Setter methods
    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int aHealth) {
        this.health = aHealth;
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

}
