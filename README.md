# Desolate Depths: A Dungeon-Crawling Adventure

## Running the Game
Class *Main* contains the main method and acts as the entry point from which to run the game.

## Current State - 6/13/24
At this time, there are four rooms to explore. Each contains various items, weapons, armor, and gold to get and
interact with.  

Each time the player enters a room, there is a chance that a monster will spawn. If this happens, the player will 
automatically enter into combat with the monster. The player can then attack using the *'attack'* command or disengage
from combat using the *'flee'* command. There are currently six monsters that the player may encounter with various
levels of difficulty.

As mentioned, in the latest iteration gold has been implemented and can be picked up with the *'get gold'*
command. Note that gold is implemented separately from items, so the command *'get all'* will not retrieve any gold present.
There are new options in the main menu as well. You can now view the top players (including the current
player) by gold obtained and by monsters killed, and you can view all past characters and their achievements.

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
*  consume [item] - consume an item such as a potion.
*  save - Save your current game state.
*  exit - Save your game and exit.
*  print - Print your game log.
