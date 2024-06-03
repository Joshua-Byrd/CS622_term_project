package edu.bu.model.items;

import com.fasterxml.jackson.annotation.*;
import edu.bu.model.entitities.Player;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.function.Consumer;

@JsonTypeName("potion")
public class Potion extends Item implements Consumable, Tradeable, Serializable {
    private double price;
    private int effectRating;
    private transient Consumer<Player> effect; // transient because lambdas are not serializable

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
        this.effectRating = effectRating;
        this.effect = effect;
    }

    @Override
    public void consume(Player player) {
        if (effect != null) {
            effect.accept(player);
            System.out.println(player.getName() + " consumed " + getName() + ".");
        }
    }

    // Getter and Setter methods for effectRating
    public int getEffectRating() {
        return effectRating;
    }

    public void setEffectRating(int effectRating) {
        this.effectRating = effectRating;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public Consumer<Player> getEffect() {
        return effect;
    }

    public void setEffect(Consumer<Player> effect) {
        this.effect = effect;
    }
}

