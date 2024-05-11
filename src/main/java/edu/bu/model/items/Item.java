package edu.bu.model.items;

/**
 * Abstract class representing a general item within the game.
 * This class provides the foundation for all items that can be held, worn, or used by entities.
 */
public abstract class Item {
    private String name;
    private String description;
    private double weight;

    public Item(String aName, String aDescription, double aWeight) {
        this.name = aName;
        this.description = aDescription;
        this.weight = aWeight;
    }

    //Getter and Setter Methods
    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double aWeight) {
        this.weight = aWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }
}

