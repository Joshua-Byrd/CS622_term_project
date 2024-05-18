package edu.bu.tests.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private TestItem item;

    @BeforeEach
    public void setUp() {
        item = new TestItem("Test Item", "A test item description", 2.5);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Item", item.getName());
        assertEquals("A test item description", item.getDescription());
        assertEquals(2.5, item.getWeight());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Item", item.getName());
    }

    @Test
    public void testSetName() {
        item.setName("New Item Name");
        assertEquals("New Item Name", item.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A test item description", item.getDescription());
    }

    @Test
    public void testSetDescription() {
        item.setDescription("New Item Description");
        assertEquals("New Item Description", item.getDescription());
    }

    @Test
    public void testGetWeight() {
        assertEquals(2.5, item.getWeight());
    }

    @Test
    public void testSetWeight() {
        item.setWeight(3.0);
        assertEquals(3.0, item.getWeight());
    }
}

