package edu.bu.controller;

import edu.bu.view.TextView;

import java.util.Scanner;

public class GameController {
    private TextView view;
    private Player player;
    private Room currentRoom;

    public GameController(TextView view, Player player, Room startingRoom) {
        this.view = view;
        this.player = player;
        this.currentRoom = startingRoom;
    }

    public void startGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                view.displayMessage(currentRoom.getDescription());
                String command = scanner.nextLine();
                processCommand(command);
            }
        }
    }

    private void processCommand(String command) {
        // Parse and execute commands
    }
}

