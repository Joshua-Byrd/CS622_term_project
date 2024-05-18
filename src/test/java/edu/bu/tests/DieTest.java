package edu.bu.tests;

import edu.bu.util.Die;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DieTest {

    /**
     * Does not PROVE that Die always returns a number between 1 and faces, but offers some
     * assurance that it works as intended.
     */
    @Test
    public void testRollDieWithinRange() {
        Die die = new Die(6);
        for (int i = 0; i < 10000; i++) {
            int roll = die.rollDie();
            assertTrue(roll >= 1 && roll <= 6, "Roll should be within range 1 to 6");
        }
    }
}

