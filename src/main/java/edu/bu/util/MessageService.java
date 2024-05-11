package edu.bu.util;

import edu.bu.controller.GameController;

/**
 * MessageService acts as an intermediary between the View/Controller and any class that
 * needs to display a message. Allows messages to be routed through the Controller to the
 * View to maintain MVC architecture.
 */
public class MessageService {
    private static GameController gameController;

    public static void registerController(GameController controller) {
        gameController = controller;
    }

    /**
     * INTENT: Utilizes the GameController to send a message to the TextView to maintain MVC
     * PRECONDITION: GameController must be instantiated and registered with the MessageService
     * POSTCONDITION: The given message is displayed to the console using the current View
     *
     * @param message a message to be displayed to the console
     */
    public static void sendMessage(String message) {

        if (gameController != null) {
            gameController.displayMessage(message);
        } else {
            System.out.println("GameController not registered with MessageService.");
        }
    }
}

