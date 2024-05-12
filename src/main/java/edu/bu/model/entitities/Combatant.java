package edu.bu.model.entitities;

import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;

import java.util.Random;

/**
 * Interface defining combat-related methods that must be implemented by any class representing
 * an entity capable of engaging in combat.
 */
public interface Combatant {
    /**
     * INTENT: To simulate combat by allowing one entity to attack another.
     * EXAMPLE: A player chooses to attack a monster, this method runs and if the attack is successful, the monster takes damage
     * PRECONDITION: The target must not be null and must be an instance of Combatant.
     * POSTCONDITION: If attack is successful, takeDamage is called with some damage
     *
     * @param aTarget The entity to be attacked.
     */
    void attack(Entity aTarget);

    /**
     * INTENT: To simulate the effect of taking damage during combat.
     * PRECONDITION: The damage amount should be a non-negative integer.
     * POSTCONDITION: The entity's health is decreased by the damage amount.
     *
     * @param aDamage The amount of damage to apply.
     */
    void takeDamage(int aDamage);

    /**
     * INTENT: Determines if an attack hits the defender based on a simulated D20 roll plus attack modifiers.
     * EXAMPLE: Given an entity with an attack rating of 15 and a defender with a defense rating of 10, the attacking
     * entity must roll a 5 to score a hit (15 + 5 = 20) >= (10 + 10 = 20)
     * PRECONDITION: The defender must not be null and must be an instance of Combatant.
     * POSTCONDITION: if attack value >= defender value, return value == true. else return value == false
     *
     * @param aDefender The defender against whom the attack roll is made.
     * @return true if the attack is successful, false otherwise.
     */
    default boolean isHit(Combatant aDefender) {
        Die d20 = new Die(20);
        Random rand = new Random();
        int attackRoll = d20.rollDie(); // Simulates a D20 roll
        int attackValue = attackRoll + this.getAttackRating();
        int defenseValue = aDefender.getDefenseRating() + 10; // Base defense modifier

        return attackValue >= defenseValue;
    }


    //Getter and Setter Methods
    int getHealth();
    void setHealth(int aHealth);
    Weapon getEquippedWeapon();
    void setEquippedWeapon(Weapon anEquippedWeapon);
    Armor getEquippedArmor();
    void setEquippedArmor(Armor anEquippedArmor);
    int getAttackRating();
    void setAttackRating(int anAttackRating);
    int getDefenseRating();
    void setDefenseRating(int aDefenseRating);
}