package edu.bu.model.items;

public class Weapon extends Item {

    //attack rating is equal to the number of sides of the die rolled to calculate damage
    private int attackRating;

    public Weapon(String aName, String aDescription, double aWeight, int anAttackRating) {
        super(aName, aDescription, aWeight);
        this.attackRating = anAttackRating;

    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }
}
