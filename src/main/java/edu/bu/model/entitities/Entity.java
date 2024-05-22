package edu.bu.model.entitities;

import edu.bu.controller.GameController;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.util.MessageService;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * The abstract class for any living creature in the game.
 */
public abstract class Entity {
    private String name;
    private String description;
    private Inventory<Item> inventory;

    public Entity(String aName, String aDescription, Inventory<Item> anInventory) {
        this.name = aName;
        this.description = aDescription;
        this.inventory = anInventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public Inventory<Item> getInventory() {
        return inventory;
    }

    public void setInventory(Inventory<Item> anInventory) {
        this.inventory = anInventory;
    }


}

