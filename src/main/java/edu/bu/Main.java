package edu.bu;

import edu.bu.controller.FacadeController;
import edu.bu.controller.GameController;
import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.FacadeModel;
import edu.bu.model.entitities.FacadeEntities;
import edu.bu.model.entitities.Player;
import edu.bu.model.items.FacadeItems;
import edu.bu.model.items.Item;
import edu.bu.model.persistence.FacadePersistence;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.view.FacadeView;
import edu.bu.view.TextView;
import java.util.ArrayList;
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
        //Get instances of facade objects
        TextView view = FacadeView.getTheInstance().createTextView();
        PlayerSaveService playerSaveService = FacadePersistence.getTheInstance().createPlayerSaveService();
        FacadeModel facadeModel = FacadeModel.getTheInstance();
        FacadeItems facadeItems = FacadeItems.getTheInstance();
        FacadeEntities facadeEntities = FacadeEntities.getTheInstance();
        FacadeController facadeController = FacadeController.getTheInstance();

        Scanner scanner = new Scanner(System.in);
        GameLogger logger = null;
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
                player = FacadeEntities.getTheInstance().createPlayer(
                        playerName,
                        "A brave adventurer",
                        10,
                        facadeModel.createRoom("Starting Room", "You are standing in the test room.", new ArrayList<Item>()),
                        facadeItems.createWeapon("dagger", "A small dagger", 1.2, 4),
                        facadeItems.createArmor("Leather armor", "a cuirass made of leather", 4.5, 4),
                        new ArrayList<Item>(),
                        0.0,
                        1,
                        0
                );
                break;
            case 2:
                try {
                    player = playerSaveService.load();
                    view.displayMessage("Character " + player.getName() + " loaded.\n");
                } catch (PlayerDataException e) {
                    System.out.println("Error loading save file: " + e.getMessage());
                    System.out.println("Starting a new game...");
                    System.out.print("Enter your name: ");
                    playerName = scanner.nextLine();
                    player =  facadeEntities.createPlayer(
                            playerName,
                            "A brave adventurer",
                            10,
                            facadeModel.createRoom("Starting Room", "You are standing in the test room.", new ArrayList<Item>()),
                            facadeItems.createWeapon("dagger", "A small dagger", 1.2, 4),
                            facadeItems.createArmor("Leather armor", "a cuirass made of leather", 4.5, 4),
                            new ArrayList<Item>(),
                            0.0,
                            1,
                            0
                    );
                }
                break;
            case 3:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
        }

        if (player != null) {
            try {
                logger = FacadePersistence.getTheInstance().createGameLogger(player.getName());
            } catch (LoggerException e) {
                view.displayMessage("Error instantiating logger: " +  e.getMessage());
            }

            GameController gameController = facadeController.createGameController(view, player,
                    player.getCurrentRoom(), playerSaveService, logger);
            gameController.startGame();
        }
    }
}

