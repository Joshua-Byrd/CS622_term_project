package edu.bu.tests.view;

import edu.bu.view.TextView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextViewTest {
    private TextView textView;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        textView = new TextView();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDisplayMessage() {
        String message = "Test message";
        textView.displayMessage(message);
        assertEquals(message, outContent.toString());
    }

    @Test
    public void testDisplayMessageEmpty() {
        textView.displayMessage("");
        assertEquals("", outContent.toString());
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
