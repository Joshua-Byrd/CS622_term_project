package edu.bu.model;

import com.fasterxml.jackson.annotation.*;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents a location that a player can travel to and occupy, possibly containing items
 */
public class Room {
    /*
    name should be a singular noun (possibly with adjectives) such as "courtyard" or "large cave", so
    that it works with the introduction when the player enters. Example: "You are in a courtyard | large cave"
     */
    private String name;
    /*
    description should be a grammatically correct description of the surroundings beginning and ending
    with a full sentence so that it can be printed immediately after the name or when a player examines
    the room. Example: "(name:)You are in a large cave. (description:) The cave is brightly lit by numerous candles."
     */
    private String description;
    private Inventory<Item> items;

    /*
    Contains room connections where the key is one of the cardinal directions, and the value is the
    Room in that direction. Example: "east" : largeCave
     */
    @JsonIgnore
    private final Map<String, Room> connections = new HashMap<>();

    // Default constructor required by Jackson
    public Room(){
        this.name = "";
        this.description = "";
        this.items = new Inventory<>(1000);
    }

    @JsonCreator
    public Room(@JsonProperty("name") String aName,
                @JsonProperty("description") String aDescription,
                @JsonProperty("items") Inventory<Item> someItems) {
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

    public Inventory<Item> getItems() {
        return items;
    }

    public void setItems(Inventory<Item> someItems) {
        this.items = someItems;
    }

    public void setConnection(String aDirection, Room aRoom) {
        connections.put(aDirection, aRoom);
    }

    public Room getConnection(String aDirection) {
        return connections.get(aDirection);
    }

    public Map<String,Room> getConnections() {
        return connections;
    }
}
