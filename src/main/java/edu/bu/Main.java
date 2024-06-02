package edu.bu;

import edu.bu.controller.FacadeController;
import edu.bu.controller.GameController;
import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.initializer.FacadeInitializer;
import edu.bu.model.FacadeModel;
import edu.bu.model.Room;
import edu.bu.model.entitities.FacadeEntities;
import edu.bu.model.entitities.Monster;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.*;
import edu.bu.model.persistence.FacadePersistence;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.util.MonsterFactory;
import edu.bu.view.FacadeView;
import edu.bu.view.TextView;

import java.util.List;
import java.util.Scanner;

/**
 * Main is the entry point to the application.
 */
public class Main {
    /**
     * The main method instantiates all necessary objects, prints a main menu and starts the GameController
     * appropriately based on the user choice.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Initialize templates if needed
        FacadeInitializer.getTheInstance().initializeTemplatesIfNeeded();

        // Load game data from templates now instead of RoomManager
        List<Room> rooms = FacadeInitializer.getTheInstance().loadRooms();
        List<Monster> monsters = FacadeInitializer.getTheInstance().loadMonsters();

        // Initialize the MonsterFactory with the loaded monster templates
        MonsterFactory.initialize(monsters);

        // Get instances of facade objects
        TextView view = FacadeView.getTheInstance().createTextView();
        PlayerSaveService playerSaveService = FacadePersistence.getTheInstance().createPlayerSaveService();
        FacadeModel facadeModel = FacadeModel.getTheInstance();
        FacadeItems facadeItems = FacadeItems.getTheInstance();
        FacadeEntities facadeEntities = FacadeEntities.getTheInstance();
        FacadeController facadeController = FacadeController.getTheInstance();

        Scanner scanner = new Scanner(System.in);
        GameLogger logger = null;
        Player player = null;

        displayMainMenu(view, playerSaveService, facadeModel, facadeItems, facadeEntities, facadeController, rooms, scanner, logger, player);
    }

    /**
     * INTENT: To display the main menu and handle user input.
     * PRECONDITION: None.
     * POSTCONDITION: The main menu is displayed and user input is handled.
     * @param view the TextView instance
     * @param playerSaveService the PlayerSaveService instance
     * @param facadeModel the FacadeModel instance
     * @param facadeItems the FacadeItems instance
     * @param facadeEntities the FacadeEntities instance
     * @param facadeController the FacadeController instance
     * @param rooms the list of loaded rooms
     * @param scanner the Scanner instance for reading user input
     * @param logger the GameLogger instance
     * @param player the Player instance
     */
    private static void displayMainMenu(TextView view, PlayerSaveService playerSaveService, FacadeModel facadeModel, FacadeItems facadeItems, FacadeEntities facadeEntities, FacadeController facadeController, List<Room> rooms, Scanner scanner, GameLogger logger, Player player) {
        while (true) {
            System.out.println("1. New Game");
            System.out.println("2. Continue");
            System.out.println("3. Instructions");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");
            String input = scanner.nextLine();
            int choice = 1;
            if (!"1".equals(input) && !"2".equals(input) && !"3".equals(input) && !"4".equals(input)) {
                System.out.println("That's not a valid option. Please select an option from the menu.");
            } else {
                choice = Integer.parseInt(input);
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String playerName = scanner.nextLine();
                    Weapon startingWeapon = facadeItems.createWeapon(
                            "dagger",
                            "A small dagger",
                            1.2,
                            4,
                            15.0);
                    Armor startingArmor = facadeItems.createArmor(
                            "leather armor",
                            "a cuirass made of leather",
                            4.5,
                            4,
                            20.0);
                    Room startingRoom = rooms.get(0); // Use the first room as the starting room
                    player = FacadeEntities.getTheInstance().createPlayer(
                            playerName,
                            "A brave adventurer",
                            20,
                            startingRoom,
                            startingWeapon,
                            startingArmor,
                            facadeItems.createInventory(50),
                            0.0,
                            1,
                            0
                    );
                    player.addItemToInventory(startingWeapon);
                    player.addItemToInventory(startingArmor);
                    break;
                case 2:
                    try {
                        player = playerSaveService.load();
                        view.displayMessage("Character " + player.getName() + " loaded.\n");
                    } catch (PlayerDataException e) {
                        System.out.println("Error loading save file: " + e.getMessage());
                        e.printStackTrace();
                        System.out.println("Starting a new game...");
                        System.out.print("Enter your name: ");
                        playerName = scanner.nextLine();
                        startingWeapon = facadeItems.createWeapon(
                                "dagger",
                                "A small dagger",
                                1.2,
                                4,
                                15.0);
                        startingArmor = facadeItems.createArmor(
                                "leather armor",
                                "a cuirass made of leather",
                                4.5,
                                4,
                                20.0);
                        startingRoom = rooms.get(0); // Use the first room as the starting room
                        player = FacadeEntities.getTheInstance().createPlayer(
                                playerName,
                                "A brave adventurer",
                                10,
                                startingRoom,
                                startingWeapon,
                                startingArmor,
                                facadeItems.createInventory(50),
                                0.0,
                                1,
                                0
                        );
                        player.addItemToInventory(startingWeapon);
                        player.addItemToInventory(startingArmor);
                    }
                    break;
                case 3:
                    displayInstructions();
                    continue; // Continue the loop to show the menu again
                case 4:
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
            }

            try {
                logger = FacadePersistence.getTheInstance().createGameLogger(player.getName());
            } catch (LoggerException e) {
                view.displayMessage("Error instantiating logger: " + e.getMessage());
            }

            GameController gameController = facadeController.createGameController(view, player,
                    player.getCurrentRoom(), playerSaveService, logger);
            gameController.startGame();
        }
    }

    /**
     * INTENT: To display game instructions to the player.
     * PRECONDITION: None.
     * POSTCONDITION: The game instructions are displayed to the console.
     */
    private static void displayInstructions() {
        System.out.println("\n\nDesolate Depths is a text adventure game where you take on the role of a brave explorer\n" +
                "questing after the legendary Luminescent Orb. At each location, you will be given a description\n" +
                "of your surroundings and any items present, and you will interact with the game through typed commands\n" +
                "The list of available commands appears below. Good luck!\n\n");
        System.out.println("*  go [direction] - Move in the specified direction (north, south, east, west).");
        System.out.println("*  get [item] - Pick up an item from the current room and add it to your inventory.");
        System.out.println("*  get [item] from [container] - Retrieve an item from an open container.");
        System.out.println("*  get all - Retrieves all items that can fit in your inventory.");
        System.out.println("*  drop [item] - Remove an item from your inventory and leave it in the current room.");
        System.out.println("*  examine [target] - Examine a room, your inventory, or a specific item.");
        System.out.println("*  wear [armor] - Wear a piece of armor from your inventory.");
        System.out.println("*  wield [weapon] - Wield a weapon from your inventory.");
        System.out.println("*  open [container] - Open a container to see its contents.");
        System.out.println("*  close [container] - Close a container.");
        System.out.println("*  attack - Attack a specified monster.");
        System.out.println("*  flee [direction] - Flee to an adjacent room.");
        System.out.println("*  save - Save your current game state.");
        System.out.println("*  exit - Save your game and exit.");
        System.out.println("*  print - Print your game log.");
        System.out.println("\nType your commands in the format shown above to interact with the game world.\n\n");
    }
}





