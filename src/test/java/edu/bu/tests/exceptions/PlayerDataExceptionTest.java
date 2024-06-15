package edu.bu.tests.exceptions;

import edu.bu.exceptions.PlayerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDataExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test error message";
        PlayerDataException exception = new PlayerDataException(message);

        assertEquals(message, exception.getMessage(), "The exception message should match the provided message.");
        assertNull(exception.getCause(), "The cause should be null when only a message is provided.");
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test error message with cause";
        Throwable cause = new Throwable("This is the cause");
        PlayerDataException exception = new PlayerDataException(message, cause);

        assertEquals(message, exception.getMessage(), "The exception message should match the provided message.");
        assertEquals(cause, exception.getCause(), "The cause should match the provided cause.");
    }

}
