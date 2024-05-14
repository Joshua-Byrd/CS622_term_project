package edu.bu.controller;

import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.entitities.Player;
import edu.bu.model.Room;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.util.MessageService;
import edu.bu.view.TextView;
import java.util.Scanner;

/**
 * GameController manages the interaction between the game's view and model.
 * It controls the flow of the game, handling user input and updating the view accordingly.
 */
public class GameController {
    private final TextView view;
    private final Player player;
    private final Room currentRoom;
    private final PlayerSaveService playerSaveService;
    private final GameLogger logger;

    public GameController(TextView aView, Player aPlayer, Room aStartingRoom,
                          PlayerSaveService aPlayerSaveService,
                          GameLogger aLogger) {
        this.view = aView;
        this.player = aPlayer;
        this.currentRoom = aStartingRoom;
        this.playerSaveService = aPlayerSaveService;
        this.logger = aLogger;
        MessageService.registerController(this);
    }

    /**
     * INTENT: To initiate and maintain the game's running state, processing user input until an exit condition is met.
     * PRECONDITION: The GameController must be properly initialized with a non-null view, player, and starting room.
     * POSTCONDITION: The game ends.
     */
    public void startGame() {
        logger.log(player.getName() + " has begun their journey.");
        view.printGreeting();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                view.displayMessage(currentRoom.getDescription() + "\n");
                view.displayMessage("Game has been started by: " + player.getName() + "\n");
                view.displayMessage("Type 'Exit' to leave the game (No other commands are implemented at this time).\n>");
                String command = scanner.nextLine();
                processCommand(command);
            }
        }
    }

    /**
     * INTENT: To handle the logic for different commands that affect the game state.
     * EXAMPLE: User enters 'Go east', the command is parsed, and the room to the east is loaded
     * PRECONDITION: The command string should not be null.
     * POSTCONDITION: Depending on the command, various aspects of the game state may be altered (to be detailed as implementation is developed).
     *
     * @param command The command string input by the user.
     */
    private void processCommand(String command) {
        if ("exit".equalsIgnoreCase(command)) {
            try {
                playerSaveService.save(player);
                logger.log(player.getName() + " quit the game.");
                logger.close();
                System.exit(0);
            } catch (PlayerDataException e) {
                System.out.println("Error saving character: " + e.getMessage());

            }
        } else if ("save".equalsIgnoreCase(command)) {
            try {
                playerSaveService.save(player);
            } catch (PlayerDataException e){
                System.out.println("Error saving character: " + e.getMessage());
            }
        }
    }

    /**
     * INTENT: To provide a centralized method for sending output messages to the TextView, ensuring all user-facing messages go through one point.
     * PRECONDITION: The message string should not be null.
     * POSTCONDITION: The message is displayed to the user through the TextView.
     *
     * @param aMessage The message to be displayed.
     */
    public void displayMessage(String aMessage) {
        view.displayMessage(aMessage);
    }
}

