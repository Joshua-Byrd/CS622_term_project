package edu.bu.model;

import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;


/**
 * Represents a location that a player can travel to and occupy, possibly containing items
 */
public class Room {

    private String name;
    private String description;
    private Inventory<Item> items;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    //default constructor required by Jackson
    public Room(){
        this.name = "";
        this.description = "";
        this.items = new Inventory<>(50);
    }

    public Room(String aName, String aDescription, Inventory<Item> someItems) {
        this.name = aName;
        this.description = aDescription;
        this.items = someItems;
    }

    //Getter and Setter methods
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

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public Inventory<Item> getItems() {
        return items;
    }

    public void setItems(Inventory<Item> items) {
        this.items = items;
    }
}
