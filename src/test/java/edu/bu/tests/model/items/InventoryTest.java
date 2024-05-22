package edu.bu.tests.model.items;

import edu.bu.model.items.Inventory;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;
import edu.bu.model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory<Item> inventory;
    private Armor armor;
    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory<Item>(50); // Initialize inventory with a maximum weight of 50
        armor = new Armor("Leather Armor", "A sturdy leather armor", 10.0, 5, 25.0);
        weapon = new Weapon("Sword", "A sharp sword", 15.0, 10, 25.0);
    }

    @Test
    public void testCanAddItem() {
        assertTrue(inventory.canAddItem(armor));
        assertTrue(inventory.canAddItem(weapon));
    }

    @Test
    public void testAddItem() {
        inventory.addItem(armor);
        assertTrue(inventory.contains(armor));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem(armor);
        inventory.removeItem(armor);
        assertFalse(inventory.contains(armor));
    }

    @Test
    public void testGetItem() {
        inventory.addItem(armor);
        Item retrievedItem = inventory.getItem(0);
        assertEquals(armor, retrievedItem);
    }

    @Test
    public void testGetAllItems() {
        inventory.addItem(armor);
        inventory.addItem(weapon);
        List<Item> items = inventory.getAllItems();
        assertTrue(items.contains(armor));
        assertTrue(items.contains(weapon));
    }

    @Test
    public void testContains() {
        inventory.addItem(armor);
        assertTrue(inventory.contains(armor));
        assertFalse(inventory.contains(weapon));
    }

    @Test
    public void testClear() {
        inventory.addItem(armor);
        inventory.addItem(weapon);
        inventory.clear();
        assertFalse(inventory.contains(armor));
        assertFalse(inventory.contains(weapon));
    }

    @Test
    public void testGetTotalWeight() {
        inventory.addItem(armor);
        inventory.addItem(weapon);
        assertEquals(25.0, inventory.getTotalWeight(), 0.01);
    }

    @Test
    public void testFindItemByName() {
        inventory.addItem(armor);
        inventory.addItem(weapon);
        assertEquals(armor, inventory.findItemByName("Leather Armor"));
        assertEquals(weapon, inventory.findItemByName("Sword"));
    }

    @Test
    public void testGetMaximumWeight() {
        assertEquals(50.0, inventory.getMaximumWeight(), 0.01);
    }

    @Test
    public void testSetMaximumWeight() {
        inventory.setMaximumWeight(100.0);
        assertEquals(100.0, inventory.getMaximumWeight(), 0.01);
    }

//    @Test
//    public void testGetSize() {
//        assertEquals(0, inventory.getSize());
//        inventory.addItem(armor);
//        assertEquals(1, inventory.getSize());
//    }
}

