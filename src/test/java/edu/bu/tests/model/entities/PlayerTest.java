package edu.bu.tests.model.entities;

import edu.bu.model.entitities.*;
import edu.bu.model.Room;
import edu.bu.model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    private Player player;
    private Weapon mockWeapon;
    private Armor mockArmor;
    private Room mockRoom;
    private Monster mockMonster;

    @BeforeEach
    public void setUp() {
        mockWeapon = mock(Weapon.class);
        mockArmor = mock(Armor.class);
        mockRoom = mock(Room.class);
        mockMonster = mock(Monster.class);

        when(mockWeapon.getAttackRating()).thenReturn(20);
        when(mockArmor.getDefenseRating()).thenReturn(5);

        player = new Player(0,
                "TestPlayer",
                "A brave adventurer",
                10,
                mockRoom,
                mockWeapon,
                mockArmor,
                new Inventory<Item>(50),
                0.0,
                1,
                0
        );
    }

    @Test
    public void testAttackSuccess() {
        when(mockMonster.getDefenseRating()).thenReturn(3);
        when(mockMonster.isAlive()).thenReturn(true);

        player.attack(mockMonster);

        verify(mockMonster, atLeastOnce()).takeDamage(ArgumentMatchers.anyInt());
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(5);
        assertEquals(5, player.getCurrentHealth());
    }

    @Test
    public void testGettersAndSetters() {
        player.setMaxHealth(20);
        assertEquals(20, player.getMaxHealth());

        player.setCurrentRoom(mockRoom);
        assertEquals(mockRoom, player.getCurrentRoom());

        player.setAttackRating(10);
        assertEquals(10, player.getAttackRating());

        player.setDefenseRating(8);
        assertEquals(8, player.getDefenseRating());

        player.setEquippedWeapon(mockWeapon);
        assertEquals(mockWeapon, player.getEquippedWeapon());

        player.setEquippedArmor(mockArmor);
        assertEquals(mockArmor, player.getEquippedArmor());

        Inventory<Item> inventory = new Inventory<Item>(50);
        player.setInventory(inventory);
        assertEquals(inventory, player.getInventory());

        player.setGoldHeld(50.0);
        assertEquals(50.0, player.getGoldHeld());

        player.setRoomsVisited(5);
        assertEquals(5, player.getRoomsVisited());

        player.setMonstersDefeated(3);
        assertEquals(3, player.getMonstersDefeated());
    }
}
