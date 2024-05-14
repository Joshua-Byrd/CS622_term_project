package edu.bu.model.items;

/**
 * Represents an armor item that can be held or equipped by a character or monster
 */
public class Armor extends Item {

    private int defenseRating;

    //default constructor required by Jackson
    public Armor(){
        super("","", 0.0);
        defenseRating = 0;
    }

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

