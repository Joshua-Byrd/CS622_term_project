package edu.bu.tests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.bu.model.entitities.Combatant;
import edu.bu.util.Die;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CombatantTest {

    private Combatant attacker;
    private Combatant defender;

    @BeforeEach
    void setUp() {
        attacker = mock(Combatant.class);
        defender = mock(Combatant.class);
    }

    @Test
    void testIsHitSuccess() {
        // Setup
        when(attacker.getAttackRating()).thenReturn(15);
        when(defender.getDefenseRating()).thenReturn(10);
        Die mockedDie = mock(Die.class);
        when(mockedDie.rollDie()).thenReturn(10); // High enough to ensure a hit
        // Replace Die instantiation with DI or factory to use mocked Die

        // Execution & Verification
        assertTrue(attacker.isHit(defender));
    }

}
