package edu.bu.tests.model.entities;

import edu.bu.model.entitities.Entity;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;

import java.util.ArrayList;

public class TestEntity extends Entity {
    public TestEntity(String aName, String aDescription, Inventory<Item> anInventory) {
        super(aName, aDescription, anInventory);
    }
}
