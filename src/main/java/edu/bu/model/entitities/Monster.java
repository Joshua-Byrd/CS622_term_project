package edu.bu.model.entitities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;
import edu.bu.util.FacadeUtil;
import edu.bu.util.MessageService;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Monster entity capable of combat within the game.
 * Monsters can engage in attacks against players and take damage.
 */
public class Monster extends Entity implements Combatant, Serializable {
    private int health;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;
    private boolean isAlive;
    public transient Die attackDie;
    private final FacadeUtil facadeUtil = FacadeUtil.getTheInstance();

    // Default constructor required for serialization/deserialization
    public Monster() {
        super("", "", new Inventory<>(0));
    }

    @JsonCreator
    public Monster(@JsonProperty("name") String aName,
                   @JsonProperty("description") String aDescription,
                   @JsonProperty("health") int aHealth,
                   @JsonProperty("equippedWeapon") Weapon aWeapon,
                   @JsonProperty("equippedArmor") Armor aArmor) {
        super(aName, aDescription, new Inventory<>(0));
        this.health = aHealth;
        this.equippedWeapon = aWeapon;
        this.equippedArmor = aArmor;
        this.attackRating = aWeapon.getAttackRating();
        this.defenseRating = aArmor.getDefenseRating();
        this.isAlive = true;
        this.attackDie = FacadeUtil.getTheInstance().createDie(20); // Default to a 20-sided die
    }

    /**
     * INTENT: To perform an attack using the Monster's equipped weapon, potentially dealing damage.
     * PRECONDITION: The target must be an instance of Player and not null.
     * POSTCONDITION 1: If attack is successful, target health -= damageTaken
     * POSTCONDITION 2: Feedback message is displayed to the screen
     *
     * @param aTarget The entity that is the target of the attack.
     */
    @Override
    public void attack(Entity aTarget) {
        if (aTarget instanceof Player) {
            facadeUtil.sendMessage("The monster attacks with its " + getEquippedWeapon().getName() + "!\n");
            Player player = (Player)aTarget;
            if (isHit(player)) {
                int damageTaken = attackDie.rollDie();
                player.takeDamage(damageTaken);
                facadeUtil.sendMessage("You are hit for " + damageTaken + " damage!\n");
            } else {
                facadeUtil.sendMessage("It misses!\n");
            }
        }
    }

    /**
     * INTENT: To decrease the Monster's health by a specified damage amount following an attack.
     * PRECONDITION: The damage amount must be a non-negative integer.
     * POSTCONDITION 1: health -= aDamage
     * POSTCONDITION 2: If health <= 0, isAlive = false
     *
     * @param aDamage The amount of damage to be applied to the Monster's health.
     */
    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    // Getter and Setter methods
    @Override
    public int getMaxHealth() {
        return health;
    }

    public void setMaxHealth(int aHealth) {
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

