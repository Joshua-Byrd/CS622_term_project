package edu.bu.controller;

import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.entitities.Player;
import edu.bu.model.Room;
import edu.bu.model.items.Inventory;
import edu.bu.model.items.Item;
import edu.bu.model.persistence.GameLogger;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.util.MessageService;
import edu.bu.view.TextView;

import java.util.List;
import java.util.Scanner;
//
///**
// * GameController manages the interaction between the game's view and model.
// * It controls the flow of the game, handling user input and updating the view accordingly.
// */
//public class GameController {
//    private final TextView view;
//    private final Player player;
//    private final Room currentRoom;
//    private final PlayerSaveService playerSaveService;
//    private final GameLogger logger;
//
//    public GameController(TextView aView, Player aPlayer, Room aStartingRoom,
//                          PlayerSaveService aPlayerSaveService,
//                          GameLogger aLogger) {
//        this.view = aView;
//        this.player = aPlayer;
//        this.currentRoom = aStartingRoom;
//        this.playerSaveService = aPlayerSaveService;
//        this.logger = aLogger;
//        MessageService.registerController(this);
//    }
//
//    /**
//     * INTENT: To initiate and maintain the game's running state, processing user input until an exit condition is met.
//     * PRECONDITION: The GameController must be properly initialized with a non-null view, player, and starting room.
//     * POSTCONDITION: The game ends.
//     */
//    public void startGame() {
//
//        logger.log(player.getName() + " has begun their journey.");
//        view.printGreeting();
//
//        try (Scanner scanner = new Scanner(System.in)) {
//            while (true) {
//                view.displayMessage(currentRoom.getDescription() + "\n");
//                view.displayMessage("Game running with character: " + player.getName() + "\n");
//                view.displayMessage("Type 'Exit' to leave the game, 'save' to save your character, or 'print log' to " +
//                        "view the log file of the current character (No other commands are implemented at this time).\n>");
//                String command = scanner.nextLine();
//                processCommand(command);
//            }
//        }
//    }
//
//    /**
//     * INTENT: To handle the logic for different commands that affect the game state.
//     * EXAMPLE: User enters 'Go east', the command is parsed, and the room to the east is loaded
//     * PRECONDITION: The command string should not be null.
//     * POSTCONDITION: Depending on the command, various aspects of the game state may be altered (to be detailed as implementation is developed).
//     *
//     * @param command The command string input by the user.
//     */
//    void processCommand(String command) {
//        if ("exit".equalsIgnoreCase(command)) {
//            try {
//                playerSaveService.save(player);
//                logger.log(player.getName() + " quit the game.");
//                logger.close();
//                System.exit(0);
//            } catch (PlayerDataException e) {
//                view.displayMessage(e.getMessage());
//                e.printStackTrace();
//            }
//        } else if ("save".equalsIgnoreCase(command)) {
//            try {
//                playerSaveService.save(player);
//                view.displayMessage("Character saved!\n");
//            } catch (PlayerDataException e){
//                view.displayMessage(e.getMessage());
//                e.printStackTrace();
//            }
//        } else if ("print log".equalsIgnoreCase(command)) {
//            try {
//                view.displayMessage("Here are the important moments in your journey:\n");
//                logger.printLog();
//            } catch (LoggerException e) {
//                view.displayMessage(e.getMessage());
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * INTENT: To provide a centralized method for sending output messages to the TextView, ensuring all user-facing messages go through one point.
//     * PRECONDITION: The message string should not be null.
//     * POSTCONDITION: The message is displayed to the user through the TextView.
//     *
//     * @param aMessage The message to be displayed.
//     */
//    public void displayMessage(String aMessage) {
//        view.displayMessage(aMessage);
//    }
//}

import java.util.Scanner;

public class GameController {
    private final TextView view;
    private final Player player;
    private Room currentRoom;
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
        view.displayMessage("Game running with character: " + player.getName() + "\n");
        displayFormattedRoomDescription(player.getCurrentRoom());

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                view.displayMessage("Enter a command: ");
                String command = scanner.nextLine();
                processCommand(command);
            }
        }
    }

    /**
     * INTENT: To parse and handle the command input by the player.
     * PRECONDITION: The command string should not be null.
     * POSTCONDITION: The command is processed and appropriate actions are taken.
     *
     * @param command The command string input by the user.
     */
    public void processCommand(String command) {
        String[] parsedCommand = parseCommand(command);
        String action = parsedCommand[0];
        String target = parsedCommand.length > 1 ? parsedCommand[1] : "";

        switch (action.toLowerCase()) {
            case "go":
                handleGoCommand(target);
                break;
            case "get":
                handleGetCommand(target);
                break;
            case "drop":
                handleDropCommand(target);
                break;
            case "examine":
                handleExamineCommand(target);
                break;
            case "save":
                handleSaveCommand();
                break;
            case "exit":
                handleExitCommand();
                break;
            case "print":
                handlePrintCommand();
                break;
            default:
                view.displayMessage("Unknown command.\n");
                break;
        }
    }

    /**
     * INTENT: To parse the command into an action and target.
     * PRECONDITION: The command string should not be null.
     * POSTCONDITION: The command is parsed into an action and target.
     *
     * @param command The command string input by the user.
     * @return A string array containing the action and target.
     */
    private String[] parseCommand(String command) {
        String[] parts = command.split(" ", 2);
        return parts.length == 2 ? parts : new String[]{parts[0], ""};
    }

    /**
     * INTENT: To handle the "go" command, changing the player's current room based on the specified direction.
     * PRECONDITION: The direction must be a valid direction (north, south, east, west).
     * POSTCONDITION: The player's current room is updated if the direction is valid, otherwise an error message is displayed.
     *
     * @param direction The direction in which to move (north, south, east, west).
     */
    private void handleGoCommand(String direction) {
        Room nextRoom = null;
        switch (direction.toLowerCase()) {
            case "north":
                nextRoom = currentRoom.getConnection("north");
                break;
            case "south":
                nextRoom = currentRoom.getConnection("south");
                break;
            case "east":
                nextRoom = currentRoom.getConnection("east");
                break;
            case "west":
                nextRoom = currentRoom.getConnection("west");
                break;
            default:
                view.displayMessage("You can't go in that direction.\n");
                return;
        }
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            currentRoom = nextRoom;
            displayFormattedRoomDescription(player.getCurrentRoom());
        } else {
            view.displayMessage("You can't go in that direction.\n");
        }
    }

    /**
     * INTENT: To handle the "get" command, picking up an item from the current room's inventory and adding it to the player's inventory.
     * PRECONDITION: The item must be present in the current room's inventory, and the player must have enough carrying capacity.
     * POSTCONDITION: The item is removed from the current room's inventory and added to the player's inventory.
     *
     * @param itemName The name of the item to pick up.
     */
    private void handleGetCommand(String itemName) {
        try {
            player.pickUpItem(itemName);
            view.displayMessage("You picked up the " + itemName + ".\n");
        } catch (IllegalArgumentException e) {
            view.displayMessage("There is no " + itemName + " here.\n");
        }
    }

    /**
     * INTENT: To handle the "drop" command, removing an item from the player's inventory and adding it to the current
     * room's inventory.
     * PRECONDITION: The item must be present in the player's inventory.
     * POSTCONDITION: The item is removed from the player's inventory and added to the current room's inventory.
     *
     * @param itemName The name of the item to drop.
     */
    private void handleDropCommand(String itemName) {
        try {
            player.dropItem(itemName);
            view.displayMessage("You dropped the " + itemName + ".\n");
        } catch (IllegalArgumentException e) {
            view.displayMessage("You aren't carrying a " + itemName + ".\n");
        }
    }

    /**
     * INTENT: To handle the "examine" command, providing a description of the specified target (room, inventory, or item).
     * PRECONDITION: The target must be a valid target (room, inventory, or an item present in the player's inventory).
     * POSTCONDITION: The description of the target is displayed.
     *
     * @param target The target to examine (room, inventory, or item).
     */
    private void handleExamineCommand(String target) {
        if (target.equalsIgnoreCase("room")) {
            displayFormattedRoomDescription(player.getCurrentRoom());
        } else if (target.equalsIgnoreCase("inventory")) {
            view.displayMessage("Your inventory contains:\n");
            for (Item item : player.getInventory().getAllItems()) {
                view.displayMessage("- " + item.getName() + ": " + item.getDescription() + "\n");
            }
        } else {
            Item item = player.getInventory().findItemByName(target);
            if (item != null) {
                view.displayMessage(item.getName() + ": " + item.getDescription() + "\n");
            } else {
                view.displayMessage("There is no " + target + " to examine.\n");
            }
        }
    }

    /**
     * INTENT: To handle the "save" command, saving the player's current state to a file.
     * PRECONDITION: None.
     * POSTCONDITION: The player's state is saved to a file.
     */
    private void handleSaveCommand() {
        try {
            playerSaveService.save(player);
            view.displayMessage("Character saved!\n");
        } catch (PlayerDataException e) {
            view.displayMessage(e.getMessage() + "\n");
        }
    }

    /**
     * INTENT: To handle the "exit" command, saving the player's state and exiting the game.
     * PRECONDITION: None.
     * POSTCONDITION: The player's state is saved, and the game exits.
     */
    private void handleExitCommand() {
        try {
            playerSaveService.save(player);
            logger.log(player.getName() + " quit the game.");
            logger.close();
            System.exit(0);
        } catch (PlayerDataException e) {
            view.displayMessage(e.getMessage() + "\n");
        }
    }

    /**
     * INTENT: To handle the "print" command, displaying the player's log.
     * PRECONDITION: None.
     * POSTCONDITION: The player's log is displayed.
     */
    private void handlePrintCommand() {
        try {
            view.displayMessage("Here are the important moments in your journey:\n");
            logger.printLog();
        } catch (LoggerException e) {
            view.displayMessage(e.getMessage() + "\n");
        }
    }

    /**
     * INTENT: Display the room name, description, and inventory contents in a formatted way.
     * PRECONDITION: room cannot be null.
     * POSTCONDITION: The formatted room description and inventory contents are displayed to the screen.
     * EXAMPLE: "You are standing in a (room.name) small stone room. (room.description) The walls are...
     * If the room has items, the message will include: "You see here: item1, item2..."
     * @param room the room to display the description.
     */
    public void displayFormattedRoomDescription(Room room) {
        StringBuilder roomDescription = new StringBuilder();
        roomDescription.append("You are standing in a ").append(room.getName()).append(".\n")
                .append(room.getDescription()).append("\n");

        Inventory<Item> roomInventory = room.getItems();
        if (roomInventory.getSize() > 0) {
            roomDescription.append("You see here: ");
            List<Item> items = roomInventory.getAllItems();
            for (int i = 0; i < items.size(); i++) {
                roomDescription.append(items.get(i).getName());
                if (i < items.size() - 1) {
                    roomDescription.append(", ");
                }
            }
            roomDescription.append(".\n");
        }

        view.displayMessage(roomDescription.toString());
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

