package edu.bu.tests.model.items;

import edu.bu.model.items.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmorTest {

    private Armor armor;

    @BeforeEach
    public void setUp() {
        armor = new Armor("Shield", "A sturdy shield", 5.0, 10);
    }

    @Test
    public void testDefaultConstructor() {
        Armor defaultArmor = new Armor();
        assertNotNull(defaultArmor);
        assertEquals("", defaultArmor.getName());
        assertEquals("", defaultArmor.getDescription());
        assertEquals(0.0, defaultArmor.getWeight());
        assertEquals(0, defaultArmor.getDefenseRating());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Shield", armor.getName());
        assertEquals("A sturdy shield", armor.getDescription());
        assertEquals(5.0, armor.getWeight());
        assertEquals(10, armor.getDefenseRating());
    }

    @Test
    public void testGetName() {
        assertEquals("Shield", armor.getName());
    }

    @Test
    public void testSetName() {
        armor.setName("Helmet");
        assertEquals("Helmet", armor.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A sturdy shield", armor.getDescription());
    }

    @Test
    public void testSetDescription() {
        armor.setDescription("A strong helmet");
        assertEquals("A strong helmet", armor.getDescription());
    }

    @Test
    public void testGetWeight() {
        assertEquals(5.0, armor.getWeight());
    }

    @Test
    public void testSetWeight() {
        armor.setWeight(3.0);
        assertEquals(3.0, armor.getWeight());
    }

    @Test
    public void testGetDefenseRating() {
        assertEquals(10, armor.getDefenseRating());
    }

    @Test
    public void testSetDefenseRating() {
        armor.setDefenseRating(15);
        assertEquals(15, armor.getDefenseRating());
    }
}
