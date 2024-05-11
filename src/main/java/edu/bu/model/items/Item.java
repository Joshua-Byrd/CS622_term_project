package edu.bu.model.items;

public abstract class Item {
    private String name;
    private String description;
    private double weight;

    public Item(String aName, String aDescription, double aWeight) {
        this.name = aName;
        this.description = aDescription;
        this.weight = aWeight;
    }

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

