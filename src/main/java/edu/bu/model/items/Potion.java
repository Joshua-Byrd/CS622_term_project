package edu.bu.model.items;

import com.fasterxml.jackson.annotation.*;
import edu.bu.model.entitities.Player;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HealthPotion.class, name = "healthPotion"),

})
@JsonTypeName("potion")
/**
 * Abstract class representing a consumable potion within the game
 */
public abstract class Potion extends Item implements Consumable, Tradeable, Serializable {
    private double price;

    // No-argument constructor for Jackson
    public Potion() {
        super("", "", 0);
        this.price = 0;
    }

    // Constructor with parameters
    @JsonCreator
    public Potion(
            @JsonProperty("name") String aName,
            @JsonProperty("description") String aDescription,
            @JsonProperty("weight") double aWeight,
            @JsonProperty("price") double aPrice) {
        super(aName, aDescription, aWeight);
        this.price = aPrice;
    }

    /**
     * Defines what occurs when the player drinks the potion
     * PRECONDITION: aPlayer must not be null
     * POSTCONDITION: aPlayer is updated with the effects of the potion
     * @param aPlayer
     */
    @Override
    public void consume(Player aPlayer){};

    //getter and setter methods
    public double getPrice() {
        return price;
    }

    public void setPrice(double aPrice) {
        this.price = aPrice;
    }


}

