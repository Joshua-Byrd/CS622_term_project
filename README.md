# Desolate Depths: A Dungeon-Crawling Adventure

Class *Main* contains the main method and acts as the entry point from which to run the game.

At this time, there are only two rooms implemented. However, the player can move between them, pick up or drop items
found within (as well as wear and wield them), and save and load their character. Also, there is a chest within one
room to experiment with opening and closing containers and using the *get..from* command to retrieve items from 
them (there is no corresponding command to put an item in a container, yet).  

## Current Commands
At this time, the following command are functional:

*  go [direction] - Move in the specified direction (north, south, east, west).
*  get [item] - Pick up an item from the current room and add it to your inventory.
*  get [item] from [container] - Retrieve an item from an open container.
*  drop [item] - Remove an item from your inventory and leave it in the current room.
*  examine [target] - Examine a room, your inventory, or a specific item.
*  wear [armor] - Wear a piece of armor from your inventory.
*  wield [weapon] - Wield a weapon from your inventory.
*  open [container] - Open a container to see its contents.
*  close [container] - Close a container.
*  save - Save your current game state.
*  exit - Save your game and exit.
*  print - Print your game log.
