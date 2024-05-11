package edu.bu.model.entitities;

import edu.bu.model.items.Item;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 * An entity that the player may buy Items from or sell Items to.  NOT IMPLEMENTED AT THIS TIME
 */
public class Merchant extends Entity {

    public Merchant(String aName, String aDescription, ArrayList<Item> anInventory) {
        super(aName, aDescription, anInventory);
    }


    public void trade() {
        // Implement trading functionality
    }
}

