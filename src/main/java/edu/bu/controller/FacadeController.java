package edu.bu.controller;

import edu.bu.database.DatabaseManager;
import edu.bu.model.entitities.Player;
import edu.bu.model.Room;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.view.TextView;

/**
 * Facade for the controller package, providing a singleton access point to the GameController functionality.
 */
public class FacadeController {

    private static FacadeController instance;
    private GameController gameController;

    // Private constructor to prevent instantiation
    private FacadeController() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeController singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeController.
     *
     * @return The singleton instance of FacadeController.
     */
    public static synchronized FacadeController getTheInstance() {
        if (instance == null) {
            instance = new FacadeController();
        }
        return instance;
    }

    /**
     * INTENT: To initialize the GameController within the facade.
     * PRECONDITION: The GameController parameters should not be null.
     * POSTCONDITION: The GameController is initialized within the facade.
     *
     * @param view              The TextView for displaying messages.
     * @param player            The Player object representing the player.
     * @param startingRoom      The Room where the game starts.
     * @param playerSaveService The service for saving player data.
     * @param logger            The GameLogger for logging game events.
     * @return The initialized GameController instance.
     */
    public GameController createGameController(TextView view, Player player, Room startingRoom,
                                               PlayerSaveService playerSaveService, GameLogger logger,
                                               DatabaseManager databaseManager) {
        this.gameController = new GameController(view, player, startingRoom, playerSaveService, logger, databaseManager);
        return gameController;
    }

    /**
     * INTENT: To start the game using the GameController.
     * PRECONDITION: The GameController must be initialized.
     * POSTCONDITION: The game loop is started, and user input is processed.
     */
    public void startGame() {
        if (gameController != null) {
            gameController.startGame();
        } else {
            throw new IllegalStateException("GameController is not initialized.");
        }
    }

    /**
     * INTENT: To process a command using the GameController.
     * PRECONDITION: The command string should not be null.
     * POSTCONDITION: The command is processed by the GameController.
     *
     * @param command The command string input by the user.
     */
    public void processCommand(String command) {
        if (gameController != null) {
            gameController.processCommand(command);
        } else {
            throw new IllegalStateException("GameController is not initialized.");
        }
    }

    /**
     * INTENT: To display a message using the GameController.
     * PRECONDITION: The message string should not be null.
     * POSTCONDITION: The message is displayed through the GameController.
     *
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        if (gameController != null) {
            gameController.displayMessage(message);
        } else {
            throw new IllegalStateException("GameController is not initialized.");
        }
    }

}

