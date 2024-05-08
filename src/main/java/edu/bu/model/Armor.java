package edu.bu.model;

public class Armor extends Item {
    private int defenseRating;

    public Armor(String description, double weight, int defenseRating) {
        super(description, weight);
        this.defenseRating = defenseRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        this.defenseRating = defenseRating;
    }
}
