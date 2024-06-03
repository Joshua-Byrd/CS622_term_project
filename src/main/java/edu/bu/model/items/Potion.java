package edu.bu.model.items;

import com.fasterxml.jackson.annotation.*;
import edu.bu.model.entitities.Player;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.function.Consumer;

@JsonTypeName("potion")
public abstract class Potion extends Item implements Consumable, Tradeable, Serializable {
    private double price;

    // No-argument constructor for Jackson
    public Potion() {
        super("", "", 2);
    }

    // Constructor with parameters
    @JsonCreator
    public Potion(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("weight") double weight,
            @JsonProperty("price") double price,
            @JsonProperty("effectRating") int effectRating,
            @JsonProperty("effect") Consumer<Player> effect) {
        super(name, description, weight);
        this.price = price;
    }

    public Potion(String name, String description, double weight, double price) {
        super(name, description, weight);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public abstract void consume(Player player);
}

