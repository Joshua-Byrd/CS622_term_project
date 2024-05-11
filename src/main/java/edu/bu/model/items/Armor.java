package edu.bu.model.items;

/**
 * Represents an armor item that can be held or equipped by a character or monster
 */
public class Armor extends Item {

    // Attacking character's attack roll is compared to defender's defense rating to
    // determine if a hit occurs
    private int defenseRating;

    public Armor(String aName, String aDescription, double aWeight, int aDefenseRating) {
        super(aName, aDescription, aWeight);
        this.defenseRating = aDefenseRating;
    }

    //Getter and Setter Methods
    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }
}

