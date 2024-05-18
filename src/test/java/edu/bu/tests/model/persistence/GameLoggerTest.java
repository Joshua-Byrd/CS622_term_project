package edu.bu.tests.model.persistence;

import edu.bu.model.persistence.GameLogger;
import edu.bu.exceptions.LoggerException;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class GameLoggerTest {

    private static final String PLAYER_NAME = "TestPlayer";
    private static final String LOG_DIRECTORY = "logs";
    private static final String LOG_FILE_PATH = LOG_DIRECTORY + File.separator + PLAYER_NAME + "_log.txt";
    private GameLogger logger;

    @BeforeEach
    public void setUp() throws IOException {
        // Clear log file if it exists
        Files.deleteIfExists(Paths.get(LOG_FILE_PATH));
        logger = GameLogger.getInstance(PLAYER_NAME);
    }

    @AfterEach
    public void tearDown() {
        // Close the logger and clear the instance
        logger.close();
        GameLogger.setInstance(null);
    }

    @Test
    public void testSingletonInstance() throws LoggerException {
        GameLogger anotherLogger = GameLogger.getInstance(PLAYER_NAME);
        assertSame(logger, anotherLogger, "Logger instances should be the same (singleton).");
    }

    @Test
    public void testLog() throws IOException {
        String testMessage = "This is a test log message.";

        logger.log(testMessage);

        // Read the log file and check the content
        BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH));
        String line = reader.readLine();
        reader.close();

        assertNotNull(line, "Log file should contain the logged message.");
        assertTrue(line.contains(testMessage), "Log file should contain the test message.");
    }

    @Test
    public void testPrintLog() throws LoggerException, IOException {
        String testMessage = "This is a test log message.";
        logger.log(testMessage);

        // Redirect system out to a ByteArrayOutputStream to capture printLog() output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        logger.printLog();

        // Reset system out
        System.setOut(System.out);

        assertTrue(outContent.toString().contains(testMessage), "Printed log should contain the test message.");
    }


    @Test
    public void testSanitizeFileName() {
        String unsafeFileName = "Test/Player:Name*?.txt";
        String sanitizedFileName = logger.sanitizeFileName(unsafeFileName);
        assertEquals("Test_Player_Name__.txt", sanitizedFileName, "Sanitized file name should replace unsafe characters.");
    }
}

