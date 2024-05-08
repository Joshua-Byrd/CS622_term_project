package edu.bu.model;

import edu.bu.util.Die;

public class Monster extends Entity implements Combatant {
    private int health;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int attackRating;
    private int defenseRating;

    public Monster(String name, String description, int health, Weapon weapon, Armor armor) {
        super(name, description);
        this.health = health;
        this.equippedWeapon = weapon;
        this.equippedArmor = armor;
        this.attackRating = weapon.getAttackRating();
        this.defenseRating = armor.getDefenseRating();
    }

    @Override
    public void attack(Entity target) {
        Player player = (Player) target;
        Die attackDie = new Die(attackRating);
        //roll for attack and notify player of result
        if (attackDie.rollDie() > player.getDefenseRating()) {
            System.out.println("You successfully attack with your " +
                    equippedWeapon.getName() + "!");
            player.takeDamage(attackDie.rollDie());
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

    public void setHealth(int health) {
        this.health = health;
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
}