package edu.bu.tests.model.entities;

import edu.bu.model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private TestEntity entity;

    @BeforeEach
    public void setUp() {
        entity = new TestEntity("TestEntity", "A test entity", new ArrayList<>());
    }

    @Test
    public void testGetName() {
        assertEquals("TestEntity", entity.getName());
    }

    @Test
    public void testSetName() {
        entity.setName("NewName");
        assertEquals("NewName", entity.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A test entity", entity.getDescription());
    }

    @Test
    public void testSetDescription() {
        entity.setDescription("New Description");
        assertEquals("New Description", entity.getDescription());
    }

    @Test
    public void testGetInventory() {
        assertNotNull(entity.getInventory());
    }

    @Test
    public void testSetInventory() {
        ArrayList<Item> newInventory = new ArrayList<>();
        entity.setInventory(newInventory);
        assertEquals(newInventory, entity.getInventory());
    }
}
