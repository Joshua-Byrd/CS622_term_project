package edu.bu.tests.model.items;

import edu.bu.model.items.Container;
import edu.bu.model.items.Item;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    private Container<Item> container;
    private Weapon sword;

    @BeforeEach
    public void setUp() {
        Inventory<Item> containerInventory = new Inventory<>(100.0);
        container = new Container<>("Chest", "A wooden chest", 5.0, false, containerInventory);
        sword = new Weapon("Sword", "A sharp sword", 10.0, 15, 50.0);
    }

    @Test
    public void testGetName() {
        assertEquals("Chest", container.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A wooden chest", container.getDescription());
    }

    @Test
    public void testGetWeight() {
        assertEquals(5.0, container.getWeight());
    }

    @Test
    public void testIsOpen() {
        assertFalse(container.isOpen());
    }

    @Test
    public void testSetOpen() {
        container.setOpen(true);
        assertTrue(container.isOpen());
    }

    @Test
    public void testOpen() {
        container.open();
        assertTrue(container.isOpen());
    }

    @Test
    public void testAddItem() {
        container.addItem(sword);
        assertTrue(container.getItems().contains(sword));
    }

    @Test
    public void testRemoveItem() {
        container.addItem(sword);
        assertTrue(container.getItems().contains(sword));
        Item removedItem = container.removeItem(sword);
        assertEquals(sword, removedItem);
        assertFalse(container.getItems().contains(sword));
    }

    @Test
    public void testGetItems() {
        container.addItem(sword);
        assertEquals(1, container.getItems().getSize());
        assertTrue(container.getItems().contains(sword));
    }
}

