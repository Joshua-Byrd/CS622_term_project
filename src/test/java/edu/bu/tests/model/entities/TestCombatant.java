package edu.bu.tests.model.entities;

import edu.bu.model.entitities.Combatant;
import edu.bu.model.entitities.Entity;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;

public class TestCombatant implements Combatant {
    private int health;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;

    public TestCombatant(int health, Weapon weapon, Armor armor) {
        this.health = health;
        this.equippedWeapon = weapon;
        this.equippedArmor = armor;
        this.attackRating = weapon.getAttackRating();
        this.defenseRating = armor.getDefenseRating();
    }

    @Override
    public void attack(Entity aTarget) {
        // Implement attack logic
    }

    @Override
    public int getMaxHealth() {
        return health;
    }

    @Override
    public void setMaxHealth(int aHealth) {
        this.health = aHealth;
    }

    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
    }

    @Override
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    @Override
    public void setEquippedWeapon(Weapon anEquippedWeapon) {
        this.equippedWeapon = anEquippedWeapon;
    }

    @Override
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    @Override
    public void setEquippedArmor(Armor anEquippedArmor) {
        this.equippedArmor = anEquippedArmor;
    }

    @Override
    public int getAttackRating() {
        return attackRating;
    }

    @Override
    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }

    @Override
    public int getDefenseRating() {
        return defenseRating;
    }

    @Override
    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }
}
