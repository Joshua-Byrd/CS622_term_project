package edu.bu.tests;

import edu.bu.model.entitities.Entity;
import edu.bu.model.items.Item;

import java.util.ArrayList;

public class TestEntity extends Entity {
    public TestEntity(String aName, String aDescription, ArrayList<Item> anInventory) {
        super(aName, aDescription, anInventory);
    }
}
