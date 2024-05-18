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

    @Test
    public void testPrintGreeting() {
        String expectedGreeting = "Welcome to \"Desolate Depths,\" a place where shadows whisper and the brave or foolish tread. \n" +
                "You are an intrepid explorer, drawn to the depths by tales of a priceless artifact, the Luminescent Orb,\n" +
                "said to rest in the deepest chamber of these forsaken caverns. Legend holds that the Orb grants immense \n" +
                "power to its bearer, a temptation too great to ignore.\n" +
                "\n" +
                "Carrying nothing but your trusty dagger and leather shirt, you stand at the entrance to the labyrinthine caverns,\n" +
                "a network of rooms and tunnels carved by unknown hands through the heart of the earth. Each step forward\n" +
                "is a step away from the light and into the storied past of the Desolate Depths. Many have entered, few\n" +
                "have returned, and none have claimed the Orb. Will you uncover its secrets, or will you too be swallowed\n" +
                "by the cavern's gaping maw? The choice is yours, adventurer.\n\n";

        textView.printGreeting();
        assertEquals(expectedGreeting, outContent.toString());
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
