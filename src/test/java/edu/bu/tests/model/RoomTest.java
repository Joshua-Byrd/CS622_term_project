package edu.bu.tests.model;

import edu.bu.model.Room;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;
    private ArrayList<Item> items;

    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        items.add(new Weapon("Sword", "A sharp sword", 3.0, 10));
        room = new Room("Test Room", "A test room description", items);
    }

    @Test
    public void testDefaultConstructor() {
        Room defaultRoom = new Room();
        assertNotNull(defaultRoom);
        assertEquals("", defaultRoom.getName());
        assertEquals("", defaultRoom.getDescription());
        assertNotNull(defaultRoom.getItems());
        assertTrue(defaultRoom.getItems().isEmpty());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Test Room", room.getName());
        assertEquals("A test room description", room.getDescription());
        assertEquals(items, room.getItems());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Room", room.getName());
    }

    @Test
    public void testSetName() {
        room.setName("New Room Name");
        assertEquals("New Room Name", room.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A test room description", room.getDescription());
    }

    @Test
    public void testSetDescription() {
        room.setDescription("New Room Description");
        assertEquals("New Room Description", room.getDescription());
    }

    @Test
    public void testGetItems() {
        assertEquals(items, room.getItems());
    }

    @Test
    public void testSetItems() {
        ArrayList<Item> newItems = new ArrayList<>();
        newItems.add(new Weapon("Axe", "A heavy axe", 5.0, 15));
        room.setItems(newItems);
        assertEquals(newItems, room.getItems());
    }

    @Test
    public void testGetAndSetNorth() {
        Room northRoom = new Room("North Room", "Room to the north", new ArrayList<>());
        room.setNorth(northRoom);
        assertEquals(northRoom, room.getNorth());
    }

    @Test
    public void testGetAndSetSouth() {
        Room southRoom = new Room("South Room", "Room to the south", new ArrayList<>());
        room.setSouth(southRoom);
        assertEquals(southRoom, room.getSouth());
    }

    @Test
    public void testGetAndSetEast() {
        Room eastRoom = new Room("East Room", "Room to the east", new ArrayList<>());
        room.setEast(eastRoom);
        assertEquals(eastRoom, room.getEast());
    }

    @Test
    public void testGetAndSetWest() {
        Room westRoom = new Room("West Room", "Room to the west", new ArrayList<>());
        room.setWest(westRoom);
        assertEquals(westRoom, room.getWest());
    }
}
