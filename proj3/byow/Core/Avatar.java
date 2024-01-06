package byow.Core;

import byow.TileEngine.Tileset;

import java.io.Serializable;

/**
 *  Describe the location of the avater,
 *  if the user has customized the name of the avater, you need to modify the name of the avater,
 *  otherwise use Tileset.AVATAR.description()
 *
 **/
public class Avatar implements Serializable {

    private int x;
    private int y;
    private String avatarName;

    public Avatar(int x, int y) {
        this.x = x;
        this.y = y;
        this.avatarName = Tileset.AVATAR.description();
    }

    public Avatar(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    /**
     *
     * @param world avater exist in.
     * @param fromX current x position
     * @param fromY current y position
     * @param toX   next x position
     * @param toY   next y position
     * @return
     */
    public boolean move(World world, int fromX, int fromY, int toX, int toY) {
        if (world.getTiles()[toX][toY].equals(Tileset.LOCKED_DOOR)) {
            world.setSingleTile(toX, toY, Tileset.UNLOCKED_DOOR);
            world.setSingleTile(fromX, fromY, Tileset.FLOOR);
            this.x = toX;
            this.y = toY;
            return true;
        } else if (world.getTiles()[toX][toY].equals(Tileset.FLOOR)) {
            world.setSingleTile(toX, toY, Tileset.AVATAR);
            world.setSingleTile(fromX, fromY, Tileset.FLOOR);
            this.x = toX;
            this.y = toY;
        }
        return false;
    }
}
