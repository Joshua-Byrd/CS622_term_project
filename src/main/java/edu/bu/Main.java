package edu.bu;

import edu.bu.controller.GameController;
import edu.bu.model.Room;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.Armor;
import edu.bu.model.items.Item;
import edu.bu.model.items.Weapon;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.view.TextView;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main is the entry point to the application. It instantiates the Player character, the Room in which the player
 * begins, and the TextView (eventually the DatabaseManager, as well). Then instantiates the GameController with
 * these objects.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextView view = new TextView();
        PlayerSaveService playerSaveService = new PlayerSaveService();
        Player player = null;
        String input;
        int choice = 1;

        System.out.println("Welcome to Desolate Depths!");

        while (true) {
            System.out.println("1. New Game");
            System.out.println("2. Continue");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            input = scanner.nextLine();
            if (!"1".equals(input) && !"2".equals(input) && !"3".equals(input)) {
                System.out.println("That's not a valid option. Please select an option from the menu.");
            } else {
                choice = Integer.parseInt(input);
                break;
            }
        }

        switch (choice) {
            case 1:
                System.out.print("Enter your name: ");
                String playerName = scanner.nextLine();
                player = new Player(
                        playerName,
                        "A brave adventurer",
                        10,
                        new Room("Starting Room", "You are standing in the test room.", new ArrayList<Item>()),
                        new Weapon("dagger", "A small dagger", 1.2, 4),
                        new Armor("Leather armor", "a cuirass made of leather", 4.5, 4),
                        new ArrayList<Item>(),
                        0.0,
                        0,
                        0
                );
                break;
            case 2:
                player = playerSaveService.load();
                if (player == null) {
                    System.out.println("No saved game found. Starting a new game instead.");
                    System.out.print("Enter your name: ");
                    playerName = scanner.nextLine();
                    player = new Player(
                            playerName,
                            "A brave adventurer",
                            10,
                            new Room("Starting Room", "You are standing in the test room.", new ArrayList<Item>()),
                            new Weapon("dagger", "A small dagger", 1.2, 4),
                            new Armor("Leather armor", "a cuirass made of leather", 4.5, 4),
                            new ArrayList<Item>(),
                            0.0,
                            0,
                            0
                    );
                }
                break;
            case 3:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Exiting the game.");
                System.exit(0);
        }

        if (player != null) {
            GameLogger logger = GameLogger.getInstance(player.getName());
            GameController gameController = new GameController(view, player, player.getCurrentRoom(), playerSaveService, logger);

            gameController.startGame();
        }
    }
}
