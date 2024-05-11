package edu.bu.controller;

import edu.bu.model.entitities.Player;
import edu.bu.model.Room;
import edu.bu.util.MessageService;
import edu.bu.view.TextView;

import java.util.Scanner;

public class GameController {
    private final TextView view;
    private Player player;
    private Room currentRoom;

    public GameController(TextView view, Player player, Room startingRoom) {
        this.view = view;
        this.player = player;
        this.currentRoom = startingRoom;
        MessageService.registerController(this);
    }


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

    private void processCommand(String command) {

    }

    public void displayMessage(String aMessage) {
        view.displayMessage(aMessage);
    }
}

