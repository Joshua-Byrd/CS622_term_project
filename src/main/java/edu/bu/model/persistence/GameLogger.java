package edu.bu.model.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameLogger {
    private static GameLogger instance;
    private static final String LOG_FILE_PATH = "game_log.txt";
    private PrintWriter printWriter;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // Private constructor to prevent instantiation
    private GameLogger() {
        try {
            // Set up PrintWriter to append to the log file.
            this.printWriter = new PrintWriter(new FileWriter(LOG_FILE_PATH, true), true);
        } catch (IOException e) {
            System.err.println("Error setting up logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Public method to get the instance of the logger
    public static synchronized GameLogger getInstance() {
        if (instance == null) {
            instance = new GameLogger();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        printWriter.println("[" + dtf.format(now) + "] " + message);
    }

    // Cleanup method to close the PrintWriter
    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
