package edu.bu.tests;

import edu.bu.exceptions.LoggerException;
import edu.bu.model.persistence.GameLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameLoggerTest {
    private GameLogger gameLogger;
    private PrintWriter mockPrintWriter;
    private DateTimeFormatter dtf;

    @BeforeEach
    public void setUp() throws LoggerException {
        // Mock the PrintWriter
        mockPrintWriter = Mockito.mock(PrintWriter.class);

        // Mock the DateTimeFormatter
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        // Use reflection to set the private PrintWriter field to the mock
        gameLogger = GameLogger.getInstance("testPlayer"); {
            {
                gameLogger.setPrintWriter(mockPrintWriter);
            }
        };
    }

    @Test
    public void testGetInstance() throws LoggerException {
        GameLogger instance = GameLogger.getInstance("testPlayer");
        assertNotNull(instance);
    }

    @Test
    public void testLog() {
        String message = "Test message";
        LocalDateTime now = LocalDateTime.now();
        String expectedOutput = "[" + dtf.format(now) + "] " + message;

        gameLogger.log(message);

        verify(mockPrintWriter, times(1)).println(expectedOutput);
    }

    @Test
    public void testClose() {
        gameLogger.close();
        verify(mockPrintWriter, times(1)).close();
    }

    @Test
    public void testSanitizeFileName() {
        String fileName = "invalid/file\\name*with?special:chars";
        String expected = "invalid_file_name_with_special_chars";

        String result = gameLogger.sanitizeFileName(fileName);
        assertEquals(expected, result);
    }

    @Test
    public void testPrintLog() throws IOException, LoggerException {
        String logFilePath = "logs/testPlayer_log.txt";
        String logContent = "Log entry 1\nLog entry 2\n";

        // Create a temporary file and write log content to it
        File logFile = new File(logFilePath);
        try (PrintWriter writer = new PrintWriter(logFile)) {
            writer.print(logContent);
        }

        // Mock System.out to capture the printLog output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        gameLogger.printLog();

        // Verify the log content is printed
        assertEquals(logContent.replace("\n", " ") + " ", outContent.toString());

        // Reset System.out
        System.setOut(System.out);

        // Clean up the temporary file
        logFile.delete();
    }
}
