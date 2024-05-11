package edu.bu.model.entitities;

import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;

import java.util.Random;

public interface Combatant {
    void attack(Entity aTarget);
    int getHealth();
    void setHealth(int aHealth);
    void takeDamage(int aDamage);
    Weapon getEquippedWeapon();
    void setEquippedWeapon(Weapon anEquippedWeapon);
    Armor getEquippedArmor();
    void setEquippedArmor(Armor anEquippedArmor);
    int getAttackRating();
    void setAttackRating(int anAttackRating);
    int getDefenseRating();
    void setDefenseRating(int aDefenseRating);

    default boolean isHit(Combatant aDefender) {
        Die d20 = new Die(20);
        Random rand = new Random();
        int attackRoll = d20.rollDie(); // Simulates a D20 roll
        int attackValue = attackRoll + this.getAttackRating();
        int defenseValue = aDefender.getDefenseRating() + 10; // Base defense modifier

        return attackValue >= defenseValue;
    }
}