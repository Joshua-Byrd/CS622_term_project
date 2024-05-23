package edu.bu.view;

/**
 * TextView is responsible for all user-facing text output within the game.
 * This class acts as the view component in the MVC architecture, presenting information and messages to the player.
 */
public class TextView {

    /**
     * INTENT: To output a specific message to the console. This method is used for direct communication with the player,
     * displaying game results, actions, and other textual information.
     * PRECONDITION: The message should not be null; however, if null is passed, it will simply print nothing.
     * POSTCONDITION: The provided message is printed to the console on the same line without a newline character.
     *
     * @param message The message to be displayed. It should be properly formatted and must not be null.
     */
    public void displayMessage(String message) {
        System.out.print(message);
    }

    /**
     * INTENT: To provide a thematic introduction to the game setting, hooking the player's interest and setting the tone
     * for their adventure in "Desolate Depths." This method is typically called once at the beginning of the game session.
     * PRECONDITION: None.
     * POSTCONDITION: The greeting message is displayed to the console.
     */
    public void printGreeting() {
        System.out.println(
                "**********************************************************************************************************************\n" +
                "*  Welcome to Desolate Depths, a place where shadows whisper and only the brave or foolish tread.                    *\n" +
                "*  You are an intrepid explorer, drawn to the depths by tales of a priceless artifact, the Luminescent Orb,          *\n" +
                "*  said to rest in the deepest chamber of these forsaken caverns. Legend holds that the Orb grants immense           *\n" +
                "*  power to its bearer, a temptation too great to ignore.                                                            *\n\n" +
                "*  Carrying nothing but your trusty dagger and leather shirt, you stand poised to begin your journey.                *\n" +
                "*  Each step forward is a step away from the light and into the storied past of the Depths. Many have entered, few   *\n" +
                "*  have returned, and none have claimed the Orb. Will you uncover its secrets, or will you too be swallowed          *\n" +
                "*  by the cavern's gaping maw?                                                                                       *\n" +
                "**********************************************************************************************************************");
    }

    

}

