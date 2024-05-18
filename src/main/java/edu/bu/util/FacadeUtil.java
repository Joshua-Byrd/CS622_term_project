package edu.bu.util;

import edu.bu.controller.GameController;

/**
 * Facade for the util package, providing a singleton access point to the util functionalities.
 */
public class FacadeUtil {

    private static FacadeUtil instance;

    // Private constructor to prevent instantiation
    private FacadeUtil() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeUtil singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeUtil.
     *
     * @return The singleton instance of FacadeUtil.
     */
    public static synchronized FacadeUtil getTheInstance() {
        if (instance == null) {
            instance = new FacadeUtil();
        }
        return instance;
    }

    // Die Methods
    public Die createDie(int faces) {
        return new Die(faces);
    }

    public int rollDie(Die die) {
        return die.rollDie();
    }

    // MessageService Methods
    public void registerController(GameController controller) {
        MessageService.registerController(controller);
    }

    public void sendMessage(String message) {
        MessageService.sendMessage(message);
    }
}
