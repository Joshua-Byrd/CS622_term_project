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

    /**
     * INTENT: To allow access to the single instance of a GameLogger. If GameLogger instance does not yet
     * exist, it is instantiated. Otherwise, the instance is returned.
     * PRECONDITION: None.
     * POSTCONDITION: Return value is the single instance of GameLogger.
     *
     * @return the instance of the GameLogger
     */
    public static synchronized GameLogger getInstance() {
        if (instance == null) {
            instance = new GameLogger();
        }
        return instance;
    }

    /**
     * INTENT: Writes the given message to the game log with a timestamp.
     * PRECONDITION: The given message must not be null.
     * POSTCONDITION: The given message has been written to the log.
     * @param message the information to be written to the log
     */
    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        printWriter.println("[" + dtf.format(now) + "] " + message);
    }

    /**
     * INTENT: To close the PrintWriter stream.
     * PRECONDITIONS: printWriter must not be null
     * POSTCONDITIONS: The PrintWriter stream is closed.
     */
    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }

}
