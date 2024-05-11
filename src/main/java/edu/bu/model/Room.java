package edu.bu.model;

/**
 * Represents a location that a player can occupy and can contain items
 */
public class Room {

    private String name;
    private String description;
    //direction variables represent the Rooms lying in those directions
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String aName, String aDescription) {
        this.name = aName;
        this.description = aDescription;
    }

    //Getters and Setter methods
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
}
