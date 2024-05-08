package edu.bu.model;

public class Weapon extends Item {
    private int attackRating;

    public Weapon(String description, double weight, int attackRating) {
        super(description, weight);
        this.attackRating = attackRating;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }
}
