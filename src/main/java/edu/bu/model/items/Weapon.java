package edu.bu.model.items;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a weapon that can be held or equipped.
 */
@JsonTypeName("weapon")
public class Weapon extends Item implements Tradeable{

    private int attackRating;
    private double price;

    //default constructor required by Jackson
    public Weapon(){
        super("", "", 0.0);
        attackRating = 0;
    }

    public Weapon(String aName, String aDescription, double aWeight, int anAttackRating, double price) {
        super(aName, aDescription, aWeight);
        this.attackRating = anAttackRating;
        this.price = price;
    }

    //Getter and Setter Methods
    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int anAttackRating) {
        this.attackRating = anAttackRating;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
