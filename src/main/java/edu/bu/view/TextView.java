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
     * Prints the logo
     */
    public void printLogo() {
        System.out.println(
                "▓█████▄ ▓█████   ██████  ▒█████   ██▓    ▄▄▄     ▄▄▄█████▓▓█████    ▓█████▄ ▓█████  ██▓███  ▄▄▄█████▓ ██░ ██   ██████ \n" +
                        "▒██▀ ██▌▓█   ▀ ▒██    ▒ ▒██▒  ██▒▓██▒   ▒████▄   ▓  ██▒ ▓▒▓█   ▀    ▒██▀ ██▌▓█   ▀ ▓██░  ██▒▓  ██▒ ▓▒▓██░ ██▒▒██    ▒ \n" +
                        "░██   █▌▒███   ░ ▓██▄   ▒██░  ██▒▒██░   ▒██  ▀█▄ ▒ ▓██░ ▒░▒███      ░██   █▌▒███   ▓██░ ██▓▒▒ ▓██░ ▒░▒██▀▀██░░ ▓██▄   \n" +
                        "░▓█▄   ▌▒▓█  ▄   ▒   ██▒▒██   ██░▒██░   ░██▄▄▄▄██░ ▓██▓ ░ ▒▓█  ▄    ░▓█▄   ▌▒▓█  ▄ ▒██▄█▓▒ ▒░ ▓██▓ ░ ░▓█ ░██   ▒   ██▒\n" +
                        "░▒████▓ ░▒████▒▒██████▒▒░ ████▓▒░░██████▒▓█   ▓██▒ ▒██▒ ░ ░▒████▒   ░▒████▓ ░▒████▒▒██▒ ░  ░  ▒██▒ ░ ░▓█▒░██▓▒██████▒▒\n" +
                        " ▒▒▓  ▒ ░░ ▒░ ░▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░ ▒░▓  ░▒▒   ▓▒█░ ▒ ░░   ░░ ▒░ ░    ▒▒▓  ▒ ░░ ▒░ ░▒▓▒░ ░  ░  ▒ ░░    ▒ ░░▒░▒▒ ▒▓▒ ▒ ░\n" +
                        " ░ ▒  ▒  ░ ░  ░░ ░▒  ░ ░  ░ ▒ ▒░ ░ ░ ▒  ░ ▒   ▒▒ ░   ░     ░ ░  ░    ░ ▒  ▒  ░ ░  ░░▒ ░         ░     ▒ ░▒░ ░░ ░▒  ░ ░\n" +
                        " ░ ░  ░    ░   ░  ░  ░  ░ ░ ░ ▒    ░ ░    ░   ▒    ░         ░       ░ ░  ░    ░   ░░         ░       ░  ░░ ░░  ░  ░  \n" +
                        "   ░       ░  ░      ░      ░ ░      ░  ░     ░  ░           ░  ░      ░       ░  ░                   ░  ░  ░      ░  \n" +
                        " ░                                                                   ░                                                "
        );
    }

    /**
     * INTENT: To provide a thematic introduction to the game setting, hooking the player's interest and setting the tone
     * for their adventure in "Desolate Depths." This method is typically called once at the beginning of the game session.
     * PRECONDITION: None.
     * POSTCONDITION: The greeting message is displayed to the console.
     */
    public void printGreeting() {
        System.out.println(
                "\n**********************************************************************************************************************\n" +
                "*  Welcome to Desolate Depths, a place where shadows whisper and only the brave or foolish tread.                    *\n" +
                "*  You are an intrepid explorer, drawn to the depths by tales of a priceless artifact, the Luminescent Orb,          *\n" +
                "*  said to rest in the deepest chamber of these forsaken caverns. Legend holds that the Orb grants immense           *\n" +
                "*  power to its bearer, a temptation too great to ignore.                                                            *\n" +
                "*                                                                                                                    *\n" +
                "*  Carrying nothing but your trusty dagger and leather shirt, you stand poised to begin your journey.                *\n" +
                "*  Each step forward is a step away from the light and into the storied past of the Depths. Many have entered, few   *\n" +
                "*  have returned, and none have claimed the Orb. Will you uncover its secrets, or will you too be swallowed          *\n" +
                "*  by the cavern's gaping maw?                                                                                       *\n" +
                "**********************************************************************************************************************\n");
    }

    /**
     * INTENT: To display game instructions to the player.
     * PRECONDITION: None.
     * POSTCONDITION: The game instructions are displayed to the console.
     */
    public void displayInstructions() {
        System.out.println("\n\nDesolate Depths is a text adventure game where you take on the role of a brave explorer\n" +
                "questing after the legendary Luminescent Orb. At each location, you will be given a description\n" +
                "of your surroundings and any items present, and you will interact with the game through typed commands.\n" +
                "The list of available commands appears below. Good luck!\n\n");
        System.out.println("*  go [direction] - Move in the specified direction (north, south, east, west).");
        System.out.println("*  get [item | gold] - Pick up the specified item or gold from the current room and add it to your inventory.");
        System.out.println("*  get [item] from [container] - Retrieve an item from an open container.");
        System.out.println("*  get all - Retrieves all items that can fit in your inventory.");
        System.out.println("*  drop [item] - Remove an item from your inventory and leave it in the current room.");
        System.out.println("*  examine [room | item | inventory | self]");
        System.out.println("*  wear [armor] - Wear a piece of armor from your inventory.");
        System.out.println("*  wield [weapon] - Wield a weapon from your inventory.");
        System.out.println("*  open [container] - Open a container to see its contents.");
        System.out.println("*  close [container] - Close a container.");
        System.out.println("*  attack - Attack the monster you're currently battling.");
        System.out.println("*  flee - Disengage from combat.");
        System.out.println("*  consume [item] - Consume an item such as a potion.");
        System.out.println("*  help - View the full list of commands.");
        System.out.println("*  save - Save your current game state.");
        System.out.println("*  exit - Save your game and exit.");
        System.out.println("*  print - Print your game log.");
        System.out.println("\nType your commands in the format shown above to interact with the game world.\n\n");
    }

}

