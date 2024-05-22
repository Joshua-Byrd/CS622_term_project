package edu.bu.tests.exceptions;

import edu.bu.exceptions.LoggerException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test error message";
        LoggerException exception = new LoggerException(message);

        assertEquals(message, exception.getMessage(), "The exception message should match the provided message.");
        assertNull(exception.getCause(), "The cause should be null when only a message is provided.");
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test error message with cause";
        Throwable cause = new Throwable("This is the cause");
        LoggerException exception = new LoggerException(message, cause);

        assertEquals(message, exception.getMessage(), "The exception message should match the provided message.");
        assertEquals(cause, exception.getCause(), "The cause should match the provided cause.");
    }

    @Test
    public void testToString() {
        String message = "Test error message";
        LoggerException exception = new LoggerException(message);

        assertEquals("LoggerException", exception.toString(), "The toString method should return 'LoggerException'.");
    }
}
