package edu.bu.model.entitities;

import edu.bu.model.items.Item;
import java.util.ArrayList;

import java.util.ArrayList;

public class Merchant extends Entity {

    public Merchant(String aName, String aDescription, ArrayList<Item> anInventory) {
        super(aName, aDescription, anInventory);
    }

    // Merchant-specific methods
    public void trade() {
        // Implement trading functionality
    }
}

