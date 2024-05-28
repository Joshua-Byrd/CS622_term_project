package edu.bu.model.items;

/**
 * Indicates an item that can be traded with a merchant (not implemented fully yet).
 * In general, players can only pick up Tradeable items, so this can also act as
 * a filter for what can be gotten in a room.
 */
public interface Tradeable {
    double getPrice();
    void setPrice(double price);
}