package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;

/**
 *
 */
public class Game {


    private final int WORLD_WIDTH;
    private final int WORLD_HEIGHT;
    private final TERenderer ter;
    private final Font fontBig = new Font("Monaco", Font.BOLD, 30);
    private final Font fontSmall = new Font("Monaco", Font.BOLD, 20);
    private final KeyboardInputSource keyInputSource;

    private boolean gameRunning;
    private Character lastCommand = null;
    private boolean gameWin = false;
    private Long avatarName;
    private World world;
    private Avatar avatar;
    private boolean switchOn = true;

    public Game(int worldWidth, int worldHeight, TERenderer ter) {
        this.WORLD_WIDTH = worldWidth;
        this.WORLD_HEIGHT = worldHeight;
        this.ter = ter;
        keyInputSource = new KeyboardInputSource();

        this.ter.initialize(worldWidth, worldHeight + 2);
    }


    public void setWorld(World world) {
        this.world = world;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }


    public World getWorld() {
        return this.world;
    }


    /**
     * show main menu,give user prompt execute next command.
     */
    public void showGameMenu() {

        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(fontBig);
        StdDraw.text(WORLD_WIDTH / 2, 4 * WORLD_HEIGHT / 5, "CS61B: THE GAME");

        StdDraw.setFont(fontSmall);
        StdDraw.text(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2, "New Game (N)");
        StdDraw.text(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, "Load Game (L)");
        StdDraw.text(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2, "Give Avatar Name (R)");
        StdDraw.text(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 4, "Quit (Q)");
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    public void drawFrame(String s, Font font, int x, int y) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(font);
        StdDraw.text(x, y, s);
    }

    public void startGame() {

        gameRunning = true;
        Character lastCommand = null;
        while (gameRunning) {
            hud();
            if (StdDraw.hasNextKeyTyped()) {
                Character command = Character.toUpperCase(StdDraw.nextKeyTyped());

                disposeUserInput(command);
            }
        }
    }

    /**
     * draw frame all the world or part of world.
     * depend on parameter switchOn.
     */
    private void hud() {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();

        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        if (world != null) {
            if (switchOn && x >= 0 && y >= 0 && x < WORLD_WIDTH && y < WORLD_HEIGHT
                    && x >= avatar.getX() - 2 && x <= avatar.getX() + 2
                    && y >= avatar.getY() - 2 && y <= avatar.getY() + 2
            ) {
                StdDraw.textLeft(1, WORLD_HEIGHT + 1, world.getTiles()[x][y].character() != Tileset.AVATAR.character()
                        ? world.getTiles()[x][y].description() : world.getAvatar().getAvatarName());
            } else if (!switchOn && x >= 0 && y >= 0 && x < WORLD_WIDTH && y < WORLD_HEIGHT) {
                StdDraw.textLeft(1, WORLD_HEIGHT + 1, world.getTiles()[x][y].character() != Tileset.AVATAR.character()
                        ? world.getTiles()[x][y].description() : world.getAvatar().getAvatarName());
            }
            StdDraw.textRight(WORLD_WIDTH - 1, WORLD_HEIGHT + 1, "M:change Mode");
            StdDraw.line(0, WORLD_HEIGHT + 0.5, WORLD_WIDTH, WORLD_HEIGHT + 0.5);

            if (gameWin) {
                gameRunning = false;
                drawFrame("You win the game", fontBig, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
                StdDraw.show();
                StdDraw.pause(1000);
            } else {
                if (switchOn) {
                    int xPos = avatar.getX();
                    int yPos = avatar.getY();

                    int Start_X = Math.max(0, xPos - 2);
                    int end_X = Math.min(xPos + 2, WORLD_WIDTH - 1);
                    int start_Y = Math.max(0, yPos - 2);
                    int end_Y = Math.min(yPos + 2, WORLD_HEIGHT - 1);

                    for (int i = Start_X; i <= end_X; ++i) {
                        for (int j = start_Y; j <= end_Y; j++) {
                            world.getTiles()[i][j].draw(i, j);
                        }
                    }
                    StdDraw.show();
                } else {
                    for (int i = 0; i < WORLD_WIDTH; i += 1) {
                        for (int j = 0; j < WORLD_HEIGHT; j += 1) {
                            world.getTiles()[i][j].draw(i, j);
                        }
                    }
                    StdDraw.show();
                }
            }
        }
    }


    /**
     *
     * @param command user single char command
     * for user's command,'N','L','R',':Q' and move command 'W','A','S','D',
     *                and change draw frame mode command 'M',
     *                execute different code.
     */
    public void disposeUserInput(Character command) {
        if (command.equals('N')) {
            drawFrame("please input your seed and end with 's'", fontSmall, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            StdDraw.show();
            StdDraw.pause(1000);

            String seed = "";
            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    Character nextKey = StdDraw.nextKeyTyped();
                    System.out.println(nextKey);
                    if (nextKey == 's') {
                        break;
                    } else {
                        seed += nextKey;
                        drawFrame("your seed:" + seed, fontSmall, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
                        StdDraw.show();
                        StdDraw.pause(200);
                    }
                }
            }
            this.avatarName = Long.parseLong(seed);
            this.world = new World(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, this.avatarName);
            world.initialWorld();
            if (avatar == null) {
                world.setAvatar();
                avatar = world.getAvatar();
            } else {
                world.setAvatar();
                world.getAvatar().setAvatarName(avatar.getAvatarName());
                avatar = world.getAvatar();
            }
        } else if (command.equals('L')) {
            this.world = World.recoverStateFromFile();
            if (world == null) {
                gameRunning = false;
            }
            this.avatarName = world.getSeed();
            this.avatar = world.getAvatar();
        } else if (command.equals('R')) {

            drawFrame("please give your avatar a name and end with 's'", fontSmall, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            StdDraw.show();
            StdDraw.pause(200);

            String avatarName = "";
            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    Character nextKey = StdDraw.nextKeyTyped();
                    System.out.println(nextKey);
                    if (nextKey == 's') {
                        break;
                    } else {
                        avatarName += nextKey;
                        drawFrame("avatar name:" + avatarName, fontSmall, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
                        StdDraw.show();
                        StdDraw.pause(200);
                    }
                }
            }
            this.avatar = new Avatar(avatarName);
            showGameMenu();
        } else if (command.equals(':')) {
            lastCommand = ':';
        } else if (command.equals('Q')) {
            if (lastCommand == ':') {
                gameRunning = false;
                world.saveCurrentWorldState();
            } else {
                lastCommand = null;
            }
        } else if (world != null && command.equals('W')) {
            gameWin = avatar.move(world, avatar.getX(), avatar.getY(), avatar.getX(), avatar.getY() + 1);
        } else if (world != null && command.equals('S')) {
            gameWin = avatar.move(world, avatar.getX(), avatar.getY(), avatar.getX(), avatar.getY() - 1);
        } else if (world != null && command.equals('A')) {
            gameWin = avatar.move(world, avatar.getX(), avatar.getY(), avatar.getX() - 1, avatar.getY());
        } else if (world != null && command.equals('D')) {
            gameWin = avatar.move(world, avatar.getX(), avatar.getY(), avatar.getX() + 1, avatar.getY());
        } else if (world != null && command.equals('M')) {
            switchOn = false;
        }
    }
}
