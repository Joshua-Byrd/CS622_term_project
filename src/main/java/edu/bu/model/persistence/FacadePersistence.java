package edu.bu.model.persistence;

import edu.bu.exceptions.LoggerException;
import edu.bu.exceptions.PlayerDataException;
import edu.bu.model.entitities.Player;

import java.io.PrintWriter;

/**
 * Facade for the persistence package, providing a singleton access point to the persistence functionalities.
 */
public class FacadePersistence {

    private static FacadePersistence instance;

    // Private constructor to prevent instantiation
    private FacadePersistence() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadePersistence singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadePersistence.
     *
     * @return The singleton instance of FacadePersistence.
     */
    public static synchronized FacadePersistence getTheInstance() {
        if (instance == null) {
            instance = new FacadePersistence();
        }
        return instance;
    }

    // GameLogger Methods
    public GameLogger createGameLogger(String playerName) throws LoggerException {
        return GameLogger.getInstance(playerName);
    }

    public void logMessage(GameLogger logger, String message) {
        logger.log(message);
    }

//    public void printLog(GameLogger logger) throws LoggerException {
//        logger.printLog();
//    }

    public void closeLogger(GameLogger logger) {
        logger.close();
    }

    public void setLoggerPrintWriter(GameLogger logger, PrintWriter printWriter) {
        logger.setPrintWriter(printWriter);
    }

    public String sanitizeLoggerFileName(GameLogger logger, String fileName) {
        return logger.sanitizeFileName(fileName);
    }

    // PlayerSaveService Methods
    public PlayerSaveService createPlayerSaveService() {
        return new PlayerSaveService();
    }

    public void savePlayer(PlayerSaveService saveService, Player player) throws PlayerDataException {
        saveService.save(player);
    }

    public Player loadPlayer(PlayerSaveService saveService) throws PlayerDataException {
        return saveService.load();
    }
}
