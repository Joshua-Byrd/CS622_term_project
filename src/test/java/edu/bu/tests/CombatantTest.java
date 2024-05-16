package edu.bu.tests;

import edu.bu.model.items.Armor;
import edu.bu.model.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatantTest {
    private TestCombatant combatant;
    private Weapon mockWeapon;
    private Armor mockArmor;

    @BeforeEach
    public void setUp() {
        mockWeapon = new Weapon("Sword", "A sharp sword", 3.0, 10);
        mockArmor = new Armor("Shield", "A sturdy shield", 5.0, 5);
        combatant = new TestCombatant(100, mockWeapon, mockArmor);
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, combatant.getHealth());
    }

    @Test
    public void testSetHealth() {
        combatant.setHealth(50);
        assertEquals(50, combatant.getHealth());
    }

    @Test
    public void testTakeDamage() {
        combatant.takeDamage(20);
        assertEquals(80, combatant.getHealth());
    }

    @Test
    public void testGetEquippedWeapon() {
        assertEquals(mockWeapon, combatant.getEquippedWeapon());
    }

    @Test
    public void testSetEquippedWeapon() {
        Weapon newWeapon = new Weapon("Axe", "A heavy axe", 5.0, 15);
        combatant.setEquippedWeapon(newWeapon);
        assertEquals(newWeapon, combatant.getEquippedWeapon());
    }

    @Test
    public void testGetEquippedArmor() {
        assertEquals(mockArmor, combatant.getEquippedArmor());
    }

    @Test
    public void testSetEquippedArmor() {
        Armor newArmor = new Armor("Helmet", "A strong helmet", 2.0, 8);
        combatant.setEquippedArmor(newArmor);
        assertEquals(newArmor, combatant.getEquippedArmor());
    }

    @Test
    public void testGetAttackRating() {
        assertEquals(10, combatant.getAttackRating());
    }

    @Test
    public void testSetAttackRating() {
        combatant.setAttackRating(15);
        assertEquals(15, combatant.getAttackRating());
    }

    @Test
    public void testGetDefenseRating() {
        assertEquals(5, combatant.getDefenseRating());
    }

    @Test
    public void testSetDefenseRating() {
        combatant.setDefenseRating(8);
        assertEquals(8, combatant.getDefenseRating());
    }

    @Test
    public void testIsHit() {
        TestCombatant defender = new TestCombatant(100, new Weapon("Dagger", "A small dagger", 1.0, 5), new Armor("Leather", "A leather armor", 2.0, 2));
        boolean result = combatant.isHit(defender);
        assertTrue(result); // Assuming combatant's attackRating + die roll will be greater than defender's defenseRating + 10
    }
}
