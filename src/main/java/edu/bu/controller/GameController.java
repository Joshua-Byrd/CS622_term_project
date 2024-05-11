package edu.bu.controller;

import edu.bu.model.entitities.Player;
import edu.bu.model.Room;
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

    public GameController(TextView view, Player player, Room startingRoom) {
        this.view = view;
        this.player = player;
        this.currentRoom = startingRoom;
        MessageService.registerController(this);
    }

    /**
     * INTENT: To initiate and maintain the game's running state, processing user input until the game ends.
     * PRECONDITION: The GameController must be properly initialized with a non-null view, player, and starting room.
     * POSTCONDITION: The game continues to run, processing user inputs until an exit condition is met (not implemented yet).
     */
    public void startGame() {
        view.printGreeting();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                view.displayMessage(currentRoom.getDescription());
                String command = scanner.nextLine();
                processCommand(command);
            }
        }
    }

    /**
     * INTENT: To handle the logic for different commands that affect the game state.
     * PRECONDITION: The command string should not be null.
     * POSTCONDITION: Depending on the command, various aspects of the game state may be altered (to be detailed as implementation is developed).
     *
     * @param command The command string input by the user.
     */
    private void processCommand(String command) {

        if ("exit".equals(command.toLowerCase())) {
            System.exit(1);
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

