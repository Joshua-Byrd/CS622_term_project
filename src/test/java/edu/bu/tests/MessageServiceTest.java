package edu.bu.tests;

import edu.bu.controller.GameController;
import edu.bu.util.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    private GameController mockGameController;

    @BeforeEach
    public void setUp() {
        mockGameController = mock(GameController.class);
        MessageService.registerController(mockGameController);
    }

    @Test
    public void testSendMessageWithRegisteredController() {
        String message = "Test message";
        MessageService.sendMessage(message);

        verify(mockGameController, times(1)).displayMessage(message);
    }

    @Test
    public void testSendMessageWithoutRegisteredController() {
        // Unregister the controller by setting it to null
        MessageService.registerController(null);

        // Capture the standard output to verify the output message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String message = "Test message";
        MessageService.sendMessage(message);

        String expectedOutput = "GameController not registered with MessageService.";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the standard output
        System.setOut(System.out);
    }
}
