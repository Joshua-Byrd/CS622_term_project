package edu.bu.exceptions;

import java.io.IOException;

/**
 * Custom exception to be thrown when an error occurs writing to or reading from the log file.
 */
public class LoggerException extends IOException{
    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "LoggerException";
    }
}
