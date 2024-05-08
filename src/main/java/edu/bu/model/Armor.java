package edu.bu.model;

public class Armor extends Item {

    //attacking character's attack roll is compared to defender's defense rating to
    //determine if a hit occurs
    private int defenseRating;

    public Armor(String name, String description, double weight, int defenseRating) {
        super(name, description, weight);
        this.defenseRating = defenseRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        this.defenseRating = defenseRating;
    }
}
