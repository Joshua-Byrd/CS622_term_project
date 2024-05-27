package edu.bu.model.items;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents an armor item that can be held or equipped by a character or monster
 */
@JsonTypeName("armor")
public class Armor extends Item implements Tradeable{

    private double price;
    private int defenseRating;

    //default constructor required by Jackson
    public Armor(){
        super("","", 0.0);
        defenseRating = 0;
    }

    public Armor(String aName, String aDescription, double aWeight, int aDefenseRating, double price) {
        super(aName, aDescription, aWeight);
        this.defenseRating = aDefenseRating;
        this.price = price;
    }

    //Getter and Setter Methods
    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int aDefenseRating) {
        this.defenseRating = aDefenseRating;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

