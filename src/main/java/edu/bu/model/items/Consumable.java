package edu.bu.model.items;

import edu.bu.model.entitities.Player;

/**
 * Represents an item that can be consumed, such as food or a potion
 */
public interface Consumable {
    void consume(Player player);
}
