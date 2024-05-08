package edu.bu.model;

public class Weapon extends Item {

    //attack rating is equal to the number of sides of the die rolled to calculate damage
    private int attackRating;

    public Weapon(String name, String description, double weight, int attackRating) {
        super(name, description, weight);
        this.attackRating = attackRating;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }
}
