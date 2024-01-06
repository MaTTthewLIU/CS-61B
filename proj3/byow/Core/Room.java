package byow.Core;

import java.io.Serializable;


/**
 * Describes the size and position of the room in the world.
 * In order to be able to serialize and save, implement the Serializable interface
 */
public class Room implements Serializable {

    private final int leftBottomX;
    private final int leftBottomY;
    private final int rightBottomX;
    private final int rightBottomY;

    private final int leftTopX;
    private final int leftTopY;

    private final int rightTopX;
    private final int rightTopY;

    private final int roomCenterX;
    private final int roomCenterY;

    private final int leftX;
    private final int rightX;
    private final int topY;
    private final int bottomY;

    public Room(int leftBottomX, int leftBottomY, int rightBottomX, int rightBottomY, int leftTopX, int leftTopY, int rightTopX, int rightTopY) {
        this.leftBottomX = leftBottomX;
        this.leftBottomY = leftBottomY;
        this.rightBottomX = rightBottomX;
        this.rightBottomY = rightBottomY;
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.rightTopX = rightTopX;
        this.rightTopY = rightTopY;
        this.leftX = leftBottomX;
        this.rightX = rightBottomX;
        this.topY = leftTopY;
        this.bottomY = rightBottomY;

        this.roomCenterX = (leftBottomX + rightBottomX) / 2;
        this.roomCenterY = (leftBottomY + leftTopY) / 2;
    }

    public int getLeftBottomX() {
        return leftBottomX;
    }

    public int getLeftBottomY() {
        return leftBottomY;
    }

    public int getRightBottomX() {
        return rightBottomX;
    }

    public int getRightBottomY() {
        return rightBottomY;
    }

    public int getLeftTopX() {
        return leftTopX;
    }

    public int getLeftTopY() {
        return leftTopY;
    }

    public int getRightTopX() {
        return rightTopX;
    }

    public int getRightTopY() {
        return rightTopY;
    }

    public int getRoomCenterX() {
        return roomCenterX;
    }

    public int getRoomCenterY() {
        return roomCenterY;
    }

    public int getRoomDistance(Room anotherRoom) {
        return Math.abs(this.roomCenterX - anotherRoom.roomCenterX)
                + Math.abs(this.roomCenterY - anotherRoom.roomCenterY);
    }
    public boolean isOverlap(Room anotherRoom) {
        if (this.rightBottomX + 1 < anotherRoom.leftBottomX || this.leftBottomX - 1 > anotherRoom.rightBottomX) {
            return false;
        } else if (this.leftTopY + 1 < anotherRoom.leftBottomY || this.leftBottomY - 1 > anotherRoom.leftTopY) {
            return false;
        } else {
            return true;
        }
    }
}
