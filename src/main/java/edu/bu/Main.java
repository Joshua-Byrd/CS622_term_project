package edu.bu;

import edu.bu.controller.FacadeController;
import edu.bu.controller.GameController;
import edu.bu.database.FacadeDatabase;
import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.initializer.FacadeInitializer;
import edu.bu.model.FacadeModel;
import edu.bu.model.Room;
import edu.bu.model.entitities.FacadeEntities;
import edu.bu.model.entitities.Monster;
import edu.bu.model.entitities.Player;
import edu.bu.model.entitities.PlayerStats;
import edu.bu.model.items.*;
import edu.bu.model.persistence.FacadePersistence;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.music.FacadeMusic;
import edu.bu.database.DatabaseManager;
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
        FacadeMusic facadeMusic = FacadeMusic.getTheInstance();
        FacadeDatabase facadeDatabase = FacadeDatabase.getTheInstance();

        // Start playing the title theme
        facadeMusic.playLogoMusic();

        Scanner scanner = new Scanner(System.in);
        GameLogger logger = null;
        Player player = null;

        //Display main log and menu
        view.printLogo();
        displayMainMenu(view, playerSaveService, facadeModel, facadeItems, facadeEntities,
                facadeController, rooms, scanner, logger, player, facadeDatabase);
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
    private static void displayMainMenu(TextView view, PlayerSaveService playerSaveService,
                                        FacadeModel facadeModel, FacadeItems facadeItems, FacadeEntities facadeEntities,
                                        FacadeController facadeController, List<Room> rooms, Scanner scanner,
                                        GameLogger logger, Player player, FacadeDatabase facadeDatabase) {
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. New Game");
            System.out.println("2. Continue");
            System.out.println("3. Instructions");
            System.out.println("4. Top Players");
            System.out.println("5. Character History");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");
            String input = scanner.nextLine();
            int choice = 1;
            if (!"1".equals(input) && !"2".equals(input) && !"3".equals(input) && !"4".equals(input)
                    && !"5".equals(input) && !"6".equals(input)) {
                System.out.println("That's not a valid option. Please select an option from the menu.");
                continue;
            } else {
                choice = Integer.parseInt(input);
            }

            switch (choice) {
                case 1:
                    player = createNewCharacter(scanner, facadeItems, rooms, facadeEntities);
                    break;
                case 2:
                    try {
                        player = playerSaveService.load();
                        if (player == null) {
                            System.out.println("Starting a new game...");
                            player = createNewCharacter(scanner, facadeItems, rooms, facadeEntities);
                        }
                    } catch (PlayerDataException e) {
                        System.out.println("Error loading character: " + e.getMessage());
                        createNewCharacter(scanner, facadeItems, rooms, facadeEntities);
                    }
                    break;
                case 3:
                    view.displayInstructions();
                    continue;
                case 4:
                    displayTopPlayers(facadeDatabase, scanner);
                    continue;
                case 5:
                    displayCharacterHistory(facadeDatabase);
                    continue;
                case 6:
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
            }

            if (player != null) {
                try {
                    logger = FacadePersistence.getTheInstance().createGameLogger(player.getName());
                } catch (LoggerException e) {
                    view.displayMessage("Error instantiating logger: " + e.getMessage());
                }

                GameController gameController = facadeController.createGameController(view, player,
                        player.getCurrentRoom(), playerSaveService, logger, facadeDatabase);
                gameController.startGame();
            }
        }
    }

    private static Player createNewCharacter(Scanner scanner, FacadeItems facadeItems, List<Room> rooms, FacadeEntities facadeEntities) {
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
        Player player = FacadeEntities.getTheInstance().createPlayer(
                0,
                playerName,
                "A brave adventurer",
                30,
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

        return player;
    }

    /**
     * INTENT: To print a submenu and display the top five players either (including the current player)
     * either by gold accumulated or monsters defeated.
     * PRECONDITION: The database must exist and be accessible.
     * POSTCONDITION: The top five players (based on the player's choice) are printed to the screen.
     * @param facadeDatabase
     * @param scanner
     */
    private static void displayTopPlayers(FacadeDatabase facadeDatabase, Scanner scanner) {
        System.out.println("1. Wealthiest Players");
        System.out.println("2. Most Dangerous Players");
        System.out.print("Please choose an option: ");
        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);

        switch (choice) {
            case 1:
                List<PlayerStats> wealthiestPlayers = facadeDatabase.getTopPlayersByGold();
                System.out.println("\n");
                System.out.println("Wealthiest Players:");
                for (PlayerStats stats : wealthiestPlayers) {
                    System.out.println("Player: " + stats.getPlayerName() + " - Gold: " + stats.getTotalGold());
                }
                System.out.println("\n");
                break;
            case 2:
                List<PlayerStats> mostDangerousPlayers = facadeDatabase.getTopPlayersByMonstersKilled();
                System.out.println("\n");
                System.out.println("Most Dangerous Players:");
                for (PlayerStats stats : mostDangerousPlayers) {
                    System.out.println("Player: " + stats.getPlayerName() + " - Monsters Killed: " + stats.getTotalMonstersKilled());
                }
                System.out.println("\n");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * INTENT: To display the statistics of past characters.
     * PRECONDITION: The database must exist and be accessible
     * POSTCONDITION: The full list of past chracters and their statistics is printed to the screen.
     * @param facadeDatabase
     */
    private static void displayCharacterHistory(FacadeDatabase facadeDatabase) {
        List<String> playerDeathDetails = facadeDatabase.getPlayerDeathDetails();
        System.out.println("\nCharacter History:");
        for (String details : playerDeathDetails) {
            System.out.println("-- * " + details + " *\n");
        }
        System.out.println("\n");
    }
}





