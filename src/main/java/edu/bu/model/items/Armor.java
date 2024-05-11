package edu.bu.model.items;

public class Armor extends Item {

    // Attacking character's attack roll is compared to defender's defense rating to
    // determine if a hit occurs
    private int defenseRating;

    public Armor(String aName, String aDescription, double aWeight, int aDefenseRating) {
        super(aName, aDescription, aWeight);
        this.defenseRating = aDefenseRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }
}

