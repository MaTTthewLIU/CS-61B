package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class Engine {
    TERenderer ter = new TERenderer();

    public static Long seed;
    World world = null;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

        Game game = new Game(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, ter);
        game.showGameMenu();
        game.startGame();
        System.exit(0);
        System.out.println("\nend of program...");
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, running both of these:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        input = input.toUpperCase();


        if (input.charAt(0) == 'N') {

            int endIdx = input.indexOf('S');
            seed = Long.parseLong(input.substring(1, endIdx));

            Game game = new Game(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, ter);

            world = new World(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, seed);
            world.initialWorld();
            world.setAvatar();
            game.setAvatar(world.getAvatar());
            game.setWorld(world);

            int i = endIdx + 1;
            while (i < input.length() && input.charAt(i) != ':') {
                Character command = input.charAt(i);
                game.disposeUserInput(command);
                ++i;
            }

            if (i < input.length() - 1 && input.substring(i, i + 2).equalsIgnoreCase(":Q")) {
                world.saveCurrentWorldState();
            }

        } else if (input.charAt(0) == 'L') {
            this.world = World.recoverStateFromFile();
            Game game = new Game(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, ter);
            game.setAvatar(world.getAvatar());
            game.setWorld(world);

            int i = 1;
            while (i < input.length() && input.charAt(i) != ':') {
                Character command = input.charAt(i);
                game.disposeUserInput(command);
                ++i;
            }
            if (i < input.length() - 1 && input.substring(i, i + 2).equalsIgnoreCase(":Q")) {
                world.saveCurrentWorldState();
            }
        }
        ter.initialize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        ter.renderFrame(world.getTiles());
        return world.getTiles();
    }
}
