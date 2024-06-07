package edu.bu.tests.model.items;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.HealthPotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {

    @Test
    void testHealthPotionSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HealthPotion healthPotion = new HealthPotion("Health Potion", "Restores health", 1.0, 10.0, 15);

        String json = objectMapper.writeValueAsString(healthPotion);
        assertNotNull(json);
    }

    @Test
    void testHealthPotionDeserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"type\":\"healthPotion\",\"name\":\"Health Potion\",\"description\":\"Restores health\",\"weight\":1.0,\"price\":10.0,\"healthRestoration\":15}";

        HealthPotion healthPotion = objectMapper.readValue(json, HealthPotion.class);
        assertNotNull(healthPotion);
        assertEquals("Health Potion", healthPotion.getName());
        assertEquals("Restores health", healthPotion.getDescription());
        assertEquals(1.0, healthPotion.getWeight());
        assertEquals(10.0, healthPotion.getPrice());
        assertEquals(15, healthPotion.getHealthRestoration());
    }

    @Test
    void testConsume() {
        HealthPotion healthPotion = new HealthPotion("Health Potion", "Restores health", 1.0, 10.0, 15);
        Player player = new Player();
        player.setCurrentHealth(5);
        player.setMaxHealth(20);

        healthPotion.consume(player);
        assertEquals(20, player.getCurrentHealth());

        player.setCurrentHealth(10);
        healthPotion.consume(player);
        assertEquals(20, player.getCurrentHealth());

        player.setCurrentHealth(8);
        healthPotion.consume(player);
        assertEquals(20, player.getCurrentHealth());
    }
}
