package edu.bu.exceptions;

import java.io.IOException;

/**
 * Facade for the exceptions package, providing a singleton access point to the exception functionalities.
 */
public class FacadeExceptions {

    private static FacadeExceptions instance;

    // Private constructor to prevent instantiation
    private FacadeExceptions() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeExceptions singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeExceptions.
     *
     * @return The singleton instance of FacadeExceptions.
     */
    public static synchronized FacadeExceptions getTheInstance() {
        if (instance == null) {
            instance = new FacadeExceptions();
        }
        return instance;
    }

    /**
     * INTENT: To create a new instance of LoggerException with the specified message.
     * PRECONDITION: The message should not be null.
     * POSTCONDITION: Returns a new instance of LoggerException with the specified message.
     *
     * @param message The message for the exception.
     * @return A new instance of LoggerException.
     */
    public LoggerException createLoggerException(String message) {
        return new LoggerException(message);
    }

    /**
     * INTENT: To create a new instance of LoggerException with the specified message and cause.
     * PRECONDITION: The message and cause should not be null.
     * POSTCONDITION: Returns a new instance of LoggerException with the specified message and cause.
     *
     * @param message The message for the exception.
     * @param cause   The cause of the exception.
     * @return A new instance of LoggerException.
     */
    public LoggerException createLoggerException(String message, Throwable cause) {
        return new LoggerException(message, cause);
    }

    /**
     * INTENT: To create a new instance of PlayerDataException with the specified message.
     * PRECONDITION: The message should not be null.
     * POSTCONDITION: Returns a new instance of PlayerDataException with the specified message.
     *
     * @param message The message for the exception.
     * @return A new instance of PlayerDataException.
     */
    public PlayerDataException createPlayerDataException(String message) {
        return new PlayerDataException(message);
    }

    /**
     * INTENT: To create a new instance of PlayerDataException with the specified message and cause.
     * PRECONDITION: The message and cause should not be null.
     * POSTCONDITION: Returns a new instance of PlayerDataException with the specified message and cause.
     *
     * @param message The message for the exception.
     * @param cause   The cause of the exception.
     * @return A new instance of PlayerDataException.
     */
    public PlayerDataException createPlayerDataException(String message, Throwable cause) {
        return new PlayerDataException(message, cause);
    }
}
