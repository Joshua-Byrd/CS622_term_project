package edu.bu.model.items;

/**
 * Represents a weapon that can be held or equipped.
 */
public class Weapon extends Item {

    private int attackRating;

    //default constructor required by Jackson
    public Weapon(){
        super("", "", 0.0);
        attackRating = 0;
    }

    public Weapon(String aName, String aDescription, double aWeight, int anAttackRating) {
        super(aName, aDescription, aWeight);
        this.attackRating = anAttackRating;
    }

    //Getter and Setter Methods
    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }
}
