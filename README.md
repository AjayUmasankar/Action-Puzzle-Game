Run Main.java from src/ui/Main.java with JavaFX installed.

Entities:
Wall - An entity that sets the boundaries of the game. It cannot be destroyed or traversed
Arrow - Picking up an arrow will allow you to shoot it once in any direction.
Sword - Picking up a sword allows you to kill up to three enemies
Boulder - An entity that is like a wall, but the player can push it by being adjacent to it and moving towards the boulder.
Door - A door requires the correct key to open.
Pit - The player will die if he walks into the pit without a Hover Potion. Enemies cannot enter a pit and die.
Hover Potion - The player can fly over pits
Key - The player can only hold one key at a time and it will open one corresponding door.
Treasure - The player can pick this up and it may be one of the win conditions for the level
Bomb - Picking up a bomb will allow the player to put a bomb on the current spot. It will blow up after a number of turns.
Floor Switch - This entity can be activated using a boulder and can be set as one of the win conditions for the level.
Invincibility Potion - All enemies will run from the player and the player can kill an enemy if he runs into them for five turns.

AI Entities:
Coward - Will always try to be as far away from the player as possible
Treasure Goblin - Will take the shortest path to the treasures across the map and pick them up
Hunter & Hound - The hunter and hound will try to close in on the player from both sides at the same time
Strategist - Will try to predict the optimal path to victory for the player and get in his way


Keybinds:
W - move up
S - move down
A - move left
D - move right
V - Place bomb
3 - Shoot arrow upwards
4 - Shoot arrow downwards
5 - Shoot arrow left
6 - Shoot arrow right
1 - Check Inventory (prints to console)
