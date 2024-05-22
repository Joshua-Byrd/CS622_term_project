package edu.bu.exceptions;

import java.io.IOException;

/**
 * Custom exception to be thrown when an error occurs with saving/loading player data.
 */
public class PlayerDataException extends IOException {
    public PlayerDataException(String message) {
        super(message);
    }

    public PlayerDataException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "PlayerDataException";
    }
}
