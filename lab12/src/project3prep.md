# Project 3 Prep

**What distinguishes a hallway from a room? How are they similar?**

Answer:Both rooms and hallways are part of a building's structure and are constructed using similar materials, such as walls, floors, and ceilings.

**Can you think of an analogy between the process of 
drawing a knight world and randomly generating a world 
using rooms and hallways?**

Answer:A knight's tour is a sequence of moves by a knight on a chessboard where the knight visits each square exactly once. 
The process of finding a knight's tour can be seen as a search through a set of possible paths, with each path representing a different arrangement of the knight's moves.

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually 
get to the full knight world.**

Answer: I am gonna generate empty world, then genearate room, hallway , add walls , then integrates all the methods together to generate the whole world 

**This question is open-ended. Write some classes 
and methods that might be useful for Project 3. Think 
about what helper methods and classes you used for the lab!**

Answer:World class:
generateEmptyWorld(int width, int height): Initializes an empty 2D grid.
generateRoom(): Generates a single room.
generateHallway(): Generates a single hallway.
connectRooms(): Connects all rooms with hallways.
addWalls(): Adds walls around rooms and hallways.
