package edu.bu.controller;


import edu.bu.model.Room;
import edu.bu.model.items.Container;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;

/**
 * RoomManager is responsible for initializing and managing the rooms in the game.
 * It sets up the initial room configurations and links rooms together.
 */
public class RoomManager {
    private Room startingRoom;

    public RoomManager() {
        initializeRooms();
    }

    /**
     * INTENT: Initializes the rooms and their respective inventories, linking them together.
     * PRECONDITION: None.
     * POSTCONDITION: Rooms are created, items are added to their inventories, and rooms are linked.
     */
    private void initializeRooms() {
        // Create items
        Weapon dagger = new Weapon("dagger", "A small dagger", 1.2, 4, 10.0);
        Container chest = new Container("Wooden Chest", "An old wooden chest", 5.0, false, new Inventory<>(100));

        // Create an inventory for the starting room and add items to it
        Inventory<Item> startingRoomInventory = new Inventory<>(50);
        startingRoomInventory.addItem(dagger);
        startingRoomInventory.addItem(chest);

        // Create the starting room
        startingRoom = new Room("Starting Room", "You are standing in the test room.", startingRoomInventory);

        // Create another room with its own inventory
        Inventory<Item> anotherRoomInventory = new Inventory<>(50);
        Room anotherRoom = new Room("Another Room", "You are in another room.", anotherRoomInventory);

        // Link rooms
        startingRoom.setEast(anotherRoom);
        anotherRoom.setWest(startingRoom);
    }

    /**
     * INTENT: Returns the starting room of the game.
     * PRECONDITION: The starting room must have been initialized.
     * POSTCONDITION: The starting room is returned.
     *
     * @return The starting room.
     */
    public Room getStartingRoom() {
        return startingRoom;
    }
}

