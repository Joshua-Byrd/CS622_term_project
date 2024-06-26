# Desolate Depths: A Dungeon-Crawling Adventure
Desolate Depths is a text-based adventure game in the style of Zork and Curses. Per the genre norm, the player is 
presented with a description of their current location and can enter commands such as 'go north', 'get shortsword', or
'consume health potion' to interact with the environment.  Additionally, the game generates random encounters with monsters 
that the player is able to engage in combat with, possibly earning gold or items.

Death in Desolate Depths is permanent. However, during gameplay, the player can enter 'save' to save their character's 
state, and it is also saved when exiting, so as long as the character is alive, gameplay can be resumed upon restart.  
During each session and upon death, the player's stats are saved to an SQLite database, and the top players and character 
history can be viewed from the main menu.

## Tech stack
Desolate Depths is written fully in Java and utilizes an SQLite database.

## Running the Game
The game can be run in one of two ways:
1. Clone the repository - follow [Github's](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository) 
instructions for cloning a repository. Open the *Main* class in your favorite IDE and run the main method.
2. Download the .jar file - from the /src directory, download DesolateDepths.jar into a dedicated directory (you'll want it to
 be in its own directory as it will create the template files when ran for the first time). In a terminal, navigate to your chosen directory and type:
 ```
    java -jar DesolateDepths.jar
 ```
**Note that you must have Java installed on your system to execute jar files.**


## Current State - 6/14/24
At this time, there are nine rooms to explore. Each contains various items, weapons, armor, and gold to get and
interact with.  

There are currently ten monsters, and each time the player enters a room, there is a chance that one will spawn. At 
this time, it is completely random, so the player may face an easily defeatable snake or an extremely difficult lich. 
In future iterations, I plan to add levels to the monsters and spawn them based on the level (i.e. a lower level monster will spawn
more frequently than a higher level). If a monster is encountered, the player will automatically enter into combat. They 
can then attack using the *'attack'* command, disengage from combat using the *'flee'* command, *'examine'* their inventory,
or *'consume'* an item. Note that these latter two actions effectively form the player's "turn" and the monster will continue 
to attack them. 

As mentioned, in the latest iteration, gold has been implemented and can be picked up with the *'get gold'*
command. The *'get all'* command will retrieve all items that can be picked and the gold, too.

## Current Commands
At this time, the following command are functional:

*  go [direction] - Move in the specified direction (north, south, east, west).
*  get [item | gold] - Pick up an item or gold from the current room and add it to your inventory.
*  get [item] from [container] - Retrieve an item from an open container.
*  get all - Retrieves all items that can fit in your inventory.
*  drop [item] - Remove an item from your inventory and leave it in the current room.
*  examine [room | item | inventory | self]
*  wear [armor] - Wear a piece of armor from your inventory.
*  wield [weapon] - Wield a weapon from your inventory.
*  open [container] - Open a container to see its contents.
*  close [container] - Close a container.
*  attack - attack the monster you're currently battling.
*  flee - disengage from combat.
*  inventory - used during combat, this will display your inventory.
*  consume [item] - consume an item such as a potion.
*  save - Save your current game state.
*  exit - Save your game and exit.
*  print - Print your game log.

## Known bugs
When a player attempts to pick up an item that will not fit into their inventory due to weight, a message saying the
item does not exist is displayed (instead of a message saying it is too heavy). I haven't yet traced this out, but it's
on my to-do list. 