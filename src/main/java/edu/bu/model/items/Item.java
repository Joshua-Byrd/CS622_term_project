package edu.bu.model.items;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
        @JsonSubTypes.Type(value = Armor.class, name = "armor")
})
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

