package edu.bu.exceptions;

/**
 * Custom exception to be thrown when an error occurs with saving/loading player data
 */
public class PlayerDataException extends Exception{
    public PlayerDataException(String message) {
        super(message);
    }

    public PlayerDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
