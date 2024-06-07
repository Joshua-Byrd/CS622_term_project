package edu.bu.tests.model.items;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    private static class ConcretePotion extends Potion {
        public ConcretePotion(String name, String description, double weight, double price) {
            super(name, description, weight, price);
        }

        @Override
        public void consume(Player player) {
            // Implement a simple consume effect for testing
            player.setCurrentHealth(player.getCurrentHealth() + 5);
        }
    }


    @Test
    void testConsume() {
        ConcretePotion potion = new ConcretePotion("Test Potion", "A test potion", 1.0, 10.0);
        Player player = new Player();
        player.setCurrentHealth(5);
        player.setMaxHealth(20);

        potion.consume(player);
        assertEquals(10, player.getCurrentHealth());
    }
}
