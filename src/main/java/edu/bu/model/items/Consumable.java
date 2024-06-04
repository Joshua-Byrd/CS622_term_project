package edu.bu.model.items;

import edu.bu.model.entitities.Player;

/**
 * Represents an item that can be consumed, such as food or a potion
 */
public interface Consumable {
    /**
     * INTENT: Defines what occurs when a player consumes the item
     * PRECONDITION: player must not be null
     * POSTCONDITION: The player is updated with the effects of the consumable
     * @param aPlayer the player to whom the effects will apply
     */
    void consume(Player aPlayer);
}
