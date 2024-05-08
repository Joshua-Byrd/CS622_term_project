package edu.bu.model;

public interface Combatant {
    void attack(Entity target);
    int getHealth();
    void takeDamage(int damage);
}