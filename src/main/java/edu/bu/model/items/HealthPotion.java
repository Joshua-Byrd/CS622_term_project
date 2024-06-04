package edu.bu.model.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.bu.model.entitities.Player;

import java.io.Serializable;

/**
 * Represents a consumable potion that restores some of the player's health.
 */
@JsonTypeName("potion")
public class HealthPotion extends Potion implements Tradeable, Consumable, Serializable {
    private int healthRestoration;

    //No-argument constructor for Jackson
    public HealthPotion(){
        super("","",0, 0);
        this.healthRestoration = 0;
    }

    @JsonCreator
    public HealthPotion(@JsonProperty("name") String aName,
                        @JsonProperty("description") String aDescription,
                        @JsonProperty("weight") double aWeight,
                        @JsonProperty("price") double aPrice,
                        @JsonProperty("healthRestoration") int aHealthRestoration) {
        super(aName, aDescription, aWeight, aPrice);
        this.healthRestoration = aHealthRestoration;
    }


    /**
     * INTENT: To restore some of the aPlayer's health on consumption
     * PRECONDITION: aPlayer must not be null
     * POSTCONDITION: aPlayer.currentHealth == the lower of either the aPlayer.maxHealth or
     * aPlayer.currentHealth += healthRestoration
     * @param aPlayer the player to whom the effects will apply
     */
    @Override
    public void consume(Player aPlayer) {
        int newHealth = aPlayer.getCurrentHealth() + healthRestoration;
        aPlayer.setCurrentHealth(Math.min(newHealth, aPlayer.getMaxHealth()));
        System.out.println("You consumed the potion and restored some health.");
    }

    //getters and setters
    public int getHealthRestoration() {
        return healthRestoration;
    }

    public void setHealthRestoration(int aHealthRestoration) {
        this.healthRestoration = aHealthRestoration;
    }
}
