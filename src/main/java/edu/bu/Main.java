package edu.bu;

import edu.bu.controller.GameController;
import edu.bu.model.Room;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.view.TextView;

import java.util.ArrayList;

/**
 * Main is the entry point to the application. It instantiates the Player character, the Room in which the player
 * begins, and the TextView (eventually the DatabaseManager, as well). Then instantiates the GameController with
 * these objects.
 */
public class Main {
    public static void main(String[] args) {

        Room startingRoom = new Room("Starting Room", "You are standing in the test room.");
        TextView view = new TextView();
        Player player = new Player(
                "Test Player",
                "A test player",
                10,
                startingRoom,
                new Weapon("dagger", "A small dagger", 1.2, 4),
                new Armor("Leather armor", "a cuirass made of leather", 4.5, 4),
                new ArrayList<Item>());

        GameController gameController = new GameController(view, player, startingRoom);

        gameController.startGame();
    }
}
