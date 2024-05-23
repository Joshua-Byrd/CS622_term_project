package edu.bu.tests.controller;


import edu.bu.controller.RoomManager;
import edu.bu.model.Room;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Container;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomManagerTest {

    private RoomManager roomManager;

    @BeforeEach
    public void setUp() {
        roomManager = new RoomManager();
    }

    @Test
    public void testGetStartingRoom() {
        Room startingRoom = roomManager.getStartingRoom();

        assertNotNull(startingRoom, "Starting room should not be null");
        assertEquals("vast courtyard", startingRoom.getName(), "Room name should be 'Starting Room'");

    }

    @Test
    public void testStartingRoomItems() {
        Room startingRoom = roomManager.getStartingRoom();
        List<Item> items = startingRoom.getItems().getAllItems();

        assertNotNull(items, "Items should not be null");
        assertEquals(2, items.size(), "Starting room should contain 2 items");

        boolean foundWeapon = false;
        boolean foundContainer = false;

        for (Item item : items) {
            if (item instanceof Weapon) {
                foundWeapon = true;
            } else if (item instanceof Container) {
                foundContainer = true;
                Container container = (Container) item;
                assertFalse(container.isOpen(), "Container should not be open");
                assertNotNull(container.getItems(), "Container should have an inventory");
                assertEquals(0, container.getItems().getSize(), "Container should contain 1 item");
            }
        }

        assertTrue(foundWeapon, "Starting room should contain a weapon");
        assertTrue(foundContainer, "Starting room should contain a container");
    }

    @Test
    public void testRoomConnections() {
        Room startingRoom = roomManager.getStartingRoom();
        Room eastRoom = startingRoom.getConnection("east");
        Room northRoom = startingRoom.getConnection("north");
        Room southRoom = startingRoom.getConnection("south");
        Room westRoom = startingRoom.getConnection("west");

        assertNull(northRoom, "North room should be null");
        assertNull(southRoom, "South room should be null");
        assertNull(westRoom, "West room should be null");
        assertNotNull(eastRoom, "East room should not be null");

        assertEquals("small stone room", eastRoom.getName(), "East room should be named 'Another Room'");
    }
}
