package edu.bu.model;



import edu.bu.model.items.Item;

import java.util.ArrayList;

/**
 * Facade for the model package, providing a singleton access point to the Room functionality.
 */
public class FacadeModel {

    private static FacadeModel instance;

    // Private constructor to prevent instantiation
    private FacadeModel() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeModel singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeModel.
     *
     * @return The singleton instance of FacadeModel.
     */
    public static synchronized FacadeModel getTheInstance() {
        if (instance == null) {
            instance = new FacadeModel();
        }
        return instance;
    }

    /**
     * INTENT: To create a new instance of Room with the default constructor.
     * PRECONDITION: None.
     * POSTCONDITION: Returns a new instance of Room with default values.
     *
     * @return A new instance of Room.
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * INTENT: To create a new instance of Room with the specified parameters.
     * PRECONDITION: The name, description, and items should not be null.
     * POSTCONDITION: Returns a new instance of Room with the specified values.
     *
     * @param name        The name of the room.
     * @param description The description of the room.
     * @param items       The items in the room.
     * @return A new instance of Room.
     */
    public Room createRoom(String name, String description, ArrayList<Item> items) {
        return new Room(name, description, items);
    }

    // Getter and Setter methods for Room properties

    public String getRoomName(Room room) {
        return room.getName();
    }

    public void setRoomName(Room room, String name) {
        room.setName(name);
    }

    public String getRoomDescription(Room room) {
        return room.getDescription();
    }

    public void setRoomDescription(Room room, String description) {
        room.setDescription(description);
    }

    public Room getRoomNorth(Room room) {
        return room.getNorth();
    }

    public void setRoomNorth(Room room, Room north) {
        room.setNorth(north);
    }

    public Room getRoomSouth(Room room) {
        return room.getSouth();
    }

    public void setRoomSouth(Room room, Room south) {
        room.setSouth(south);
    }

    public Room getRoomEast(Room room) {
        return room.getEast();
    }

    public void setRoomEast(Room room, Room east) {
        room.setEast(east);
    }

    public Room getRoomWest(Room room) {
        return room.getWest();
    }

    public void setRoomWest(Room room, Room west) {
        room.setWest(west);
    }

    public ArrayList<Item> getRoomItems(Room room) {
        return room.getItems();
    }

    public void setRoomItems(Room room, ArrayList<Item> items) {
        room.setItems(items);
    }
}
