package edu.bu.tests.model.items;

import edu.bu.model.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        weapon = new Weapon("Sword", "A sharp sword", 3.0, 10, 25.0);
    }

    @Test
    public void testDefaultConstructor() {
        Weapon defaultWeapon = new Weapon();
        assertNotNull(defaultWeapon);
        assertEquals("", defaultWeapon.getName());
        assertEquals("", defaultWeapon.getDescription());
        assertEquals(0.0, defaultWeapon.getWeight());
        assertEquals(0, defaultWeapon.getAttackRating());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Sword", weapon.getName());
        assertEquals("A sharp sword", weapon.getDescription());
        assertEquals(3.0, weapon.getWeight());
        assertEquals(10, weapon.getAttackRating());
    }

    @Test
    public void testGetName() {
        assertEquals("Sword", weapon.getName());
    }

    @Test
    public void testSetName() {
        weapon.setName("Axe");
        assertEquals("Axe", weapon.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A sharp sword", weapon.getDescription());
    }

    @Test
    public void testSetDescription() {
        weapon.setDescription("A heavy axe");
        assertEquals("A heavy axe", weapon.getDescription());
    }

    @Test
    public void testGetWeight() {
        assertEquals(3.0, weapon.getWeight());
    }

    @Test
    public void testSetWeight() {
        weapon.setWeight(5.0);
        assertEquals(5.0, weapon.getWeight());
    }

    @Test
    public void testGetAttackRating() {
        assertEquals(10, weapon.getAttackRating());
    }

    @Test
    public void testSetAttackRating() {
        weapon.setAttackRating(15);
        assertEquals(15, weapon.getAttackRating());
    }
}
