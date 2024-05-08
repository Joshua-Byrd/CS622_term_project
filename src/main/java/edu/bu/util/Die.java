package edu.bu.util;

import java.util.Random;

public class Die {
    private int faces;

    public Die(int faces) {
        this.faces = faces;
    }

    public int rollDie() {
        Random random = new Random();
        return random.nextInt(faces) + 1;
    }
}
