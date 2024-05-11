package edu.bu.model.entitities;

import edu.bu.controller.GameController;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.util.Die;
import edu.bu.util.MessageService;

import java.util.ArrayList;

import java.util.ArrayList;

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

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int aHealth) {
        this.health = aHealth;
    }

    @Override
    public void takeDamage(int aDamage) {
        this.health -= aDamage;
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
