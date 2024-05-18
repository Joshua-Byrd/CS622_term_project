package edu.bu.tests;

import edu.bu.model.entitities.*;
import edu.bu.model.Room;
import edu.bu.model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;

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

        when(mockWeapon.getAttackRating()).thenReturn(5);
        when(mockArmor.getDefenseRating()).thenReturn(5);

        player = new Player(
                "TestPlayer",
                "A brave adventurer",
                10,
                mockRoom,
                mockWeapon,
                mockArmor,
                new ArrayList<>(),
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
    public void testAttackFailure() {
        when(mockMonster.getDefenseRating()).thenReturn(10);

        player.attack(mockMonster);

        verify(mockMonster, never()).takeDamage(ArgumentMatchers.anyInt());
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(5);
        assertEquals(5, player.getHealth());
    }

    @Test
    public void testUpdateCurrentWeight() {
        List<Item> inventory = new ArrayList<>();
        inventory.add(new Weapon("Sword", "A sharp sword", 3.0, 10));
        inventory.add(new Armor("Shield", "A sturdy shield", 5.0, 5));

        player.setInventory((ArrayList<Item>) inventory);
        double updatedWeight = player.updateCurrentWeight((ArrayList<Item>) inventory);

        assertEquals(8.0, updatedWeight);
    }

    @Test
    public void testGettersAndSetters() {
        player.setHealth(20);
        assertEquals(20, player.getHealth());

        player.setCurrentRoom(mockRoom);
        assertEquals(mockRoom, player.getCurrentRoom());

        player.setAttackRating(10);
        assertEquals(10, player.getAttackRating());

        player.setDefenseRating(8);
        assertEquals(8, player.getDefenseRating());

        player.setCurrentWeight(15.0);
        assertEquals(15.0, player.getCurrentWeight());

        player.setCarryingCapacity(100.0);
        assertEquals(100.0, player.getCarryingCapacity());

        player.setEquippedWeapon(mockWeapon);
        assertEquals(mockWeapon, player.getEquippedWeapon());

        player.setEquippedArmor(mockArmor);
        assertEquals(mockArmor, player.getEquippedArmor());

        ArrayList<Item> inventory = new ArrayList<>();
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
