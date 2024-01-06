# Build Your Own World Design Document

**Partner 1:**

**Partner 2:**

## Classes and Data Structures
TETile: represents a single tile in the world with a character, text color, background color, description, and optional image filepath. It is immutable, but can be copied or have a color variant created.
Tileset: contains constant tile objects for easy use in the code.


## Algorithms
RandomUtils: a helper class with static methods for generating random numbers and shuffling arrays.
World: a class with a static method generate(int width, int height, long seed) that generates a hexagonal world with the given width and height using the given seed.
It first generates a hexagonal grid of Tile objects using the Hexagon class.
It then assigns each tile in the grid a corresponding TETile object based on its position in the world using a series of conditional statements.
Finally, it returns the resulting 2D array of TETile objects.

## Persistence
