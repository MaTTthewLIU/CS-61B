package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.*;
import java.util.*;


/**
 *  Describes the entire world, including width and height, rooms, and the position of the avater.
 *  Randomly create rooms based on user input and link rooms together.
 *  Entire worlds can be restored from files. In order to save the object, the Serializable interface is implemented.
 */
public class World implements Serializable {

    private static final long serialVersionUID = -9135871198781197891L;

    private TETile[][] tiles;

    private List<Room> rooms = new ArrayList<>();

    private final int width;

    private final int height;

    private final long seed;
    private final Random random;
    private Avatar avatar;

    public World(int width, int height, Long seed) {
        tiles = new TETile[width][height];
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.random = new Random(seed);
    }


    public TETile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSingleTile(int x, int y, TETile tile) {
        this.tiles[x][y] = tile;
    }

    public void printWorld() {
        for (int j = height - 1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                System.out.print(tiles[i][j].character());
            }
            System.out.println();
        }
    }

    public void createRooms() {

        int roomCount = 30 + RandomUtils.uniform(random, 40);

        System.out.println("roomCount:" + roomCount);

        while (roomCount > 0) {
            int leftBottomX = RandomUtils.uniform(random, width);
            int leftBottomY = RandomUtils.uniform(random, height);

            int roomWidth = RandomUtils.uniform(random, 2, 7);
            int roomHeight = RandomUtils.uniform(random, 2, 5);


            if (leftBottomX > 0 && leftBottomY > 0 && leftBottomX + roomWidth - 1 < width - 1 && leftBottomY + roomHeight - 1 < height - 1) {
                Room room = new Room(leftBottomX, leftBottomY,
                        leftBottomX + roomWidth - 1, leftBottomY,
                        leftBottomX, leftBottomY + roomHeight - 1,
                        leftBottomX + roomWidth - 1, leftBottomY + roomHeight - 1);
                boolean overlap = false;
                for (Room existRoom : rooms) {
                    if (room.isOverlap(existRoom)) {
                        overlap = true;
                        break;
                    }
                }
                if (!overlap) {
                    rooms.add(room);
                    --roomCount;
                }
            }
        }

        for (Room room : rooms) {
            int X_start = room.getLeftBottomX();
            int Y_start = room.getLeftBottomY();
            int X_end = room.getRightTopX();
            int Y_end = room.getRightTopY();
            for (int i = X_start; i <= X_end; i++) {
                for (int j = Y_start; j <= Y_end; j++) {
                    setSingleTile(i, j, Tileset.FLOOR);
                }
            }
        }
    }

    public void buildHallways() {
        Map<Room, List<Integer>> roomDistance = createRoomDistance();

        Room startRoom = rooms.get(0);
        Set<Room> visitedRoom = new HashSet<>();
        visitedRoom.add(startRoom);

        // find the nearest room
        connectNearestRoom(roomDistance, startRoom, visitedRoom);

    }

    private void connectNearestRoom(Map<Room, List<Integer>> roomDistance, Room startRoom, Set<Room> visitedRoom) {
        if (visitedRoom.size() == roomDistance.size()) {
            return;
        }
        List<Integer> adjRoomDistance = roomDistance.get(startRoom);
        double minDistance = Integer.MAX_VALUE;
        Room nextRoom = null;

        for (int i = 0; i < adjRoomDistance.size(); i++) {
            Room room = rooms.get(i);
            if (!visitedRoom.contains(room)) {
                if (adjRoomDistance.get(i) < minDistance) {
                    minDistance = adjRoomDistance.get(i);
                    nextRoom = room;
                }
            }
        }

        if (startRoom.getLeftBottomY() > nextRoom.getLeftTopY()) {
            connectUpDownRoom(startRoom, nextRoom);
        } else if (nextRoom.getLeftBottomY() > startRoom.getLeftTopY()) {
            connectUpDownRoom(nextRoom, startRoom);
        } else {
            if (startRoom.getLeftBottomX() > nextRoom.getRightBottomX()) {
                connectLeftRightRoom(nextRoom, startRoom);
            } else {
                connectLeftRightRoom(startRoom, nextRoom);
            }
        }

        visitedRoom.add(nextRoom);
        connectNearestRoom(roomDistance, nextRoom, visitedRoom);

    }

    private void connectLeftRightRoom(Room leftRoom, Room rightRoom) {
        int p1X = leftRoom.getRightBottomX();
        int p1Y = leftRoom.getRoomCenterY();

        int p2X = (leftRoom.getRightBottomX() + rightRoom.getLeftBottomX()) / 2;
        int p2Y = p1Y;

        int p3X = p2X;
        int p3Y = rightRoom.getRoomCenterY();

        int p4X = rightRoom.getLeftBottomX();
        int p4Y = p3Y;

        connectTwoPoints(p1X, p1Y, p2X, p2Y);
        connectTwoPoints(p2X, p2Y, p3X, p3Y);
        connectTwoPoints(p3X, p3Y, p4X, p4Y);
    }

    private void connectUpDownRoom(Room upRoom, Room downRoom) {
        if (upRoom.getRightBottomX() < downRoom.getLeftBottomX()) {

            connectTwoPoints(upRoom.getRightBottomX(), upRoom.getRoomCenterY(),
                    downRoom.getRoomCenterX(), upRoom.getRoomCenterY());
            connectTwoPoints(downRoom.getRoomCenterX(), downRoom.getLeftTopY(),
                    downRoom.getRoomCenterX(), upRoom.getRoomCenterY());

        } else if (upRoom.getLeftBottomX() > downRoom.getRightBottomX()) {

            connectTwoPoints(downRoom.getRoomCenterX(), upRoom.getRoomCenterY(),
                    upRoom.getLeftBottomX(), upRoom.getRoomCenterY());
            connectTwoPoints(downRoom.getRoomCenterX(), downRoom.getLeftTopY(),
                    downRoom.getRoomCenterX(), upRoom.getRoomCenterY());

        } else {

            int p1X = upRoom.getRoomCenterX();
            int p1Y = upRoom.getLeftBottomY();

            int p2X = p1X;
            int p2Y = (upRoom.getRightBottomY() + downRoom.getRightTopY()) / 2;

            int p3X = downRoom.getRoomCenterX();
            int p3Y = p2Y;

            int p4X = p3X;
            int p4Y = downRoom.getLeftTopY();

            connectTwoPoints(p1X, p1Y, p2X, p2Y);
            connectTwoPoints(p2X, p2Y, p3X, p3Y);
            connectTwoPoints(p3X, p3Y, p4X, p4Y);
        }
    }

    private void connectTwoPoints(int startX, int startY, int endX, int endY) {
        if (startY == endY) {
            if (startX < endX) {
                for (int i = startX; i <= endX; ++i) {
                    setSingleTile(i, startY, Tileset.FLOOR);
                }
            } else {
                for (int i = startX; i >= endX; --i) {
                    setSingleTile(i, startY, Tileset.FLOOR);
                }
            }
        } else if (startX == endX) {
            if (startY < endY) {
                for (int j = startY; j <= endY; ++j) {
                    setSingleTile(startX, j, Tileset.FLOOR);
                }
            } else {
                for (int j = startY; j >= endY; --j) {
                    setSingleTile(startX, j, Tileset.FLOOR);
                }
            }
        }
    }


    private Map<Room, List<Integer>> createRoomDistance() {
//        Map<Room, Map<Room, Double>> adjRoomDistance = new HashMap<>();
        Map<Room, List<Integer>> adjRoomDistance = new HashMap<>();
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            adjRoomDistance.put(room, new ArrayList<>());
            for (int j = 0; j < rooms.size(); j++) {
                if (i != j) {
                    int roomDistance = room.getRoomDistance(rooms.get(j));
                    adjRoomDistance.get(room).add(roomDistance);
                } else {
                    adjRoomDistance.get(room).add(Integer.MAX_VALUE);
                }
            }
        }
        return adjRoomDistance;
    }

    public void buildWall() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] == Tileset.FLOOR) {
                    if (i + 1 < width && tiles[i + 1][j] == Tileset.NOTHING) {
                        setSingleTile(i + 1, j, Tileset.WALL);
                    }
                    if (i - 1 >= 0 && tiles[i - 1][j] == Tileset.NOTHING) {
                        setSingleTile(i - 1, j, Tileset.WALL);
                    }
                    if (j + 1 < height && tiles[i][j + 1] == Tileset.NOTHING) {
                        setSingleTile(i, j + 1, Tileset.WALL);
                    }
                    if (j - 1 >= 0 && tiles[i][j - 1] == Tileset.NOTHING) {
                        setSingleTile(i, j - 1, Tileset.WALL);
                    }
                }
            }
        }
    }

    public void setDoor() {
        int availableDoorPosition = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].equals(Tileset.WALL)) {
                    if ((i - 1 >= 0 && i + 1 < width &&
                            ((tiles[i - 1][j].equals(Tileset.NOTHING) && tiles[i + 1][j].equals(Tileset.FLOOR))
                                    || (tiles[i - 1][j].equals(Tileset.FLOOR) && tiles[i + 1][j].equals(Tileset.NOTHING))))
                            ||
                            (j - 1 >= 0 && j + 1 < height &&
                                    ((tiles[i][j - 1].equals(Tileset.NOTHING) && tiles[i][j + 1].equals(Tileset.FLOOR))
                                            || (tiles[i][j - 1].equals(Tileset.FLOOR) && tiles[i][j - 1].equals(Tileset.NOTHING))))
                    ) {
                        availableDoorPosition++;
                    }
                }
            }
        }
        int pos = RandomUtils.uniform(random, availableDoorPosition);
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].equals(Tileset.WALL)) {
                    if ((i - 1 >= 0 && i + 1 < width &&
                            ((tiles[i - 1][j].equals(Tileset.NOTHING) && tiles[i + 1][j].equals(Tileset.FLOOR))
                                    || (tiles[i - 1][j].equals(Tileset.FLOOR) && tiles[i + 1][j].equals(Tileset.NOTHING))))
                            ||
                            (j - 1 >= 0 && j + 1 < height &&
                                    ((tiles[i][j - 1].equals(Tileset.NOTHING) && tiles[i][j + 1].equals(Tileset.FLOOR))
                                            || (tiles[i][j - 1].equals(Tileset.FLOOR) && tiles[i][j - 1].equals(Tileset.NOTHING))))
                    ) {
                        if (count == pos) {
                            setSingleTile(i, j, Tileset.LOCKED_DOOR);
                            return;
                        }
                        ++count;
                    }
                }
            }
        }

    }

    public void initialWorld() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                setSingleTile(i, j, Tileset.NOTHING);
            }
        }
        createRooms();
        buildHallways();
        buildWall();
        setDoor();
        printWorld();
    }

    public void setAvatar() {
        int uniform = RandomUtils.uniform(random, rooms.size());
        Room room = rooms.get(uniform);
        int x = RandomUtils.uniform(random, room.getLeftBottomX(), room.getRightBottomX() + 1);
        int y = RandomUtils.uniform(random, room.getLeftBottomY(), room.getLeftBottomY() + 1);
        this.avatar = new Avatar(x, y);
        setSingleTile(x, y, Tileset.AVATAR);
    }


    public Avatar getAvatar() {
        return avatar;
    }

    public void saveCurrentWorldState() {
        try {
            FileOutputStream fos = new FileOutputStream(Constants.STATE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static World recoverStateFromFile() {
        FileInputStream fis = null;
        World world = null;
        try {
            fis = new FileInputStream(Constants.STATE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            world = (World) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return world;
        }
        return world;
    }

    public Long getSeed() {
        return seed;
    }
}
