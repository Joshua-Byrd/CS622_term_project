package edu.bu.model.items;

import edu.bu.model.entitities.Player;

public class HealthPotion extends Potion {
    private int healthRestoration;

    public HealthPotion(String name, String description, double weight, double price, int healthRestoration) {
        super(name, description, weight, price);
        this.healthRestoration = healthRestoration;
    }

    @Override
    public void consume(Player player) {
        int newHealth = player.getCurrentHealth() + healthRestoration;
        player.setCurrentHealth(Math.min(newHealth, player.getMaxHealth()));
        System.out.println("You consumed the potion and restored some health.");
    }

    public int getHealthRestoration() {
        return healthRestoration;
    }

    public void setHealthRestoration(int healthRestoration) {
        this.healthRestoration = healthRestoration;
    }
}
