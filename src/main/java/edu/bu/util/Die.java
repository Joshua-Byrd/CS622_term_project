package edu.bu.util;

import java.util.Random;

/**
 * Represents a die with the given number of faces that can be rolled to generate a random result between
 * 1 and the number of faces.This class is used in the game to simulate random outcomes in various scenarios,
 * such as combat.
 */
public class Die {
    private final int faces;
    static Random random = new Random();

    public Die(int faces) {
        this.faces = faces;
    }

    /**
     * INTENT: To simulate the roll of a die with this number of faces.
     * PRECONDITION: The Die must have been initialized.
     * POSTCONDITION: 1 <= return value <= number of faces
     *
     * @return An integer result of the die roll, within the range of 1 to the number of faces.
     */
    public int rollDie() {
        return random.nextInt(faces) + 1;
    }
}
