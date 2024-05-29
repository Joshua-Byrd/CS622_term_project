package edu.bu.model;

import com.fasterxml.jackson.annotation.*;
import edu.bu.model.entitities.Monster;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a location that a player can travel to and occupy, possibly containing items
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "room")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Room implements Serializable {
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
    private Map<String, Room> connections = new HashMap<>();

    //List of monsters currently in the room
    private List<Monster> monsters = new ArrayList<>();

    // Default constructor required by Jackson
    public Room() {
        this.name = "";
        this.description = "";
        this.items = new Inventory<>(1000);
        this.connections = new HashMap<>();

    }

    @JsonCreator
    public Room(@JsonProperty("name") String aName,
                @JsonProperty("description") String aDescription,
                @JsonProperty("items") Inventory<Item> someItems) {
        this.name = aName;
        this.description = aDescription;
        this.items = someItems;
        this.connections = new HashMap<>();
    }

    /**
     * INTENT: To get a monster from the room by name.
     * PRECONDITION: The name must not be null.
     * POSTCONDITION: Returns the monster with the given name, or null if no such monster exists.
     *
     * @param name The name of the monster to find.
     * @return The monster with the given name, or null if no such monster exists.
     */
    public Monster getMonsterByName(String name) {
        for (Monster monster : monsters) {
            if (monster.getName().equalsIgnoreCase(name) && monster.isAlive()) {
                return monster;
            }
        }
        return null;
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

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }



    @JsonProperty("connections")
    public Map<String,Room> getConnections() {
        return connections;
    }

    @JsonProperty("connections")
    public void setConnections(Map<String, Room> someConnections) {
        this.connections = someConnections;
    }
}
