# Desolate Depths: A Dungeon-Crawling Adventure

Class *Main* contains the main method and acts as the entry point from which to run the game.

At this time, there are only two rooms implemented. However, the player can move between them, pick up or drop items
found within (as well as wear and wield them), and save and load their character. Also, there is a chest within one
room to experiment with opening and closing containers and using the *get..from* command to retrieve items from 
them (there is no corresponding command to put an item in a container, yet). 

Each time the player enters a room, there is a chance that a monster will spawn. If this happens, the player will 
automatically enter into combat with the monster. The player can then attack using the *attack* command or disengage
from combat using the *flee* command.

In the latest implementation the player can pick up and use potions using the *consume* command.  At this time,
there is only a health potion implemented, which, when consumed, restores some of the player's health.  To see
the effects, use the *examine self* command to view health and other stats.

## Current Commands
At this time, the following command are functional:

*  go [direction] - Move in the specified direction (north, south, east, west).
*  get [item] - Pick up an item from the current room and add it to your inventory.
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
