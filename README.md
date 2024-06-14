# Desolate Depths: A Dungeon-Crawling Adventure

## Running the Game
Class *Main* contains the main method and acts as the entry point from which to run the game.

## Current State - 6/14/24
At this time, there are nine rooms to explore. Each contains various items, weapons, armor, and gold to get and
interact with.  

There are currently ten monsters, and each time the player enters a room, there is a chance that one will spawn. At 
this time, it is completely random, so the player may face an easily defeatable snake or an extremely difficult lich. 
In future iterations, I plan to add levels to the monsters and spawn them based on the level (i.e. a lower level monster will spawn
more frequently than a higher level). If a monster is encountered, the player automatically enter into combat. The player 
can then attack using the *'attack'* command, disengage from combat using the *'flee'* command, *'examine'* their inventory,
or *'consume'* an item. Note that these latter two actions effectively form the player's "turn" and the monster will continue 
to attack. 

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
*  inventory - used during combat, this will display your inventory.
*  consume [item] - consume an item such as a potion.
*  save - Save your current game state.
*  exit - Save your game and exit.
*  print - Print your game log.

## Known bugs
When a player attempts to pick up an item that will not fit into their inventory due to weight, a message saying the
item does not exist is displayed (instead of a message saying it is too heavy). I haven't yet traced this out, but it's
on my to-do list. 