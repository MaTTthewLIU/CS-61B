package knightworld;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;


/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    // Change tiles color
    public static final TETile LOCKED_DOOR = new TETile('█', StdDraw.GRAY, StdDraw.BLACK, "locked door");
    // Constructor
    public KnightWorld(int width, int height, int holeSize) {
        tiles = new TETile[width][height];
        createWorld(width, height, holeSize);
    }

    private void createWorld(int width, int height, int holeSize) {
        // Fill the world with LOCKED_DOOR
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = LOCKED_DOOR;
            }
        }

        // Place holes according to the knight's move pattern with the specified hole size
        int start_x_0 = 1 * holeSize;
        int start_x_1 = 4 * holeSize;
        int start_x_2 = 2 * holeSize;
        int start_x_3 = 0 * holeSize;
        int start_x_4 = 3 * holeSize;


        int y = 0;
        while (y < height) {
            int x = -1;
            switch (y / holeSize % 5) {
                case 0:
                    x = start_x_0;
                    break;
                case 1:
                    x = start_x_1;
                    break;
                case 2:
                    x = start_x_2;
                    break;
                case 3:
                    x = start_x_3;
                    break;
                case 4:
                    x = start_x_4;
                    break;
            }
            while (x < width) {
                placeHole(x, y, holeSize, width, height);
                x += 5 * holeSize;
            }
            y += holeSize;
        }

    }

    private void placeHole(int x, int y, int holeSize, int width, int height) {
        for (int i = 0; i < holeSize; i++) {
            for (int j = 0; j < holeSize; j++) {
                if (x + i < width && y + j < height) {
                    tiles[x + i][y + j] = Tileset.NOTHING;
                }
            }
        }
    }

    /**
     * Returns the tiles associated with this KnightWorld.
     */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 110;
        int height = 50;
        int holeSize = 6;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
