package Game_Objects;
import Game_Objects.Entities.*;
import Game_Objects.Tiles.*;
import java.util.HashMap;

public abstract class GameObject {

    protected boolean isTraversable;
    protected boolean isDestructible;
    private boolean isFalling;

    // Hashmap to map tile tokens to their respective tile subclass
    // NOTE: called OBJECT_TOKEN_MAP since it may be used with entities as well
    private static final HashMap<String, GameObject> OBJECT_TOKEN_MAP = new HashMap<>();
    static {
        OBJECT_TOKEN_MAP.put("P", new Path());
        OBJECT_TOKEN_MAP.put("D", new Dirt());
        OBJECT_TOKEN_MAP.put("E", new Exit());
        OBJECT_TOKEN_MAP.put("RK", new Key(Colours.RED));
        OBJECT_TOKEN_MAP.put("YK", new Key(Colours.YELLOW));
        OBJECT_TOKEN_MAP.put("GK", new Key(Colours.GREEN));
        OBJECT_TOKEN_MAP.put("BK", new Key(Colours.BLUE));
        OBJECT_TOKEN_MAP.put("RD", new LockedDoor(Colours.RED));
        OBJECT_TOKEN_MAP.put("YD", new LockedDoor(Colours.YELLOW));
        OBJECT_TOKEN_MAP.put("GD", new LockedDoor(Colours.GREEN));
        OBJECT_TOKEN_MAP.put("BD", new LockedDoor(Colours.BLUE));
        OBJECT_TOKEN_MAP.put("W", new Wall());
        OBJECT_TOKEN_MAP.put("M", new MagicWall());
        OBJECT_TOKEN_MAP.put("T", new TitaniumWall());
        OBJECT_TOKEN_MAP.put("A", new Amoeba());
        OBJECT_TOKEN_MAP.put("B", new Boulder());
        OBJECT_TOKEN_MAP.put("BL", new Butterfly("L"));
        OBJECT_TOKEN_MAP.put("BR", new Butterfly("R"));
        OBJECT_TOKEN_MAP.put("*", new Diamond());
        OBJECT_TOKEN_MAP.put("FL", new Firefly("L"));
        OBJECT_TOKEN_MAP.put("FR", new Firefly("R"));
        OBJECT_TOKEN_MAP.put("F", new Frog());
    }

    // Inheritable attributes shared by entities and tiles
    protected int x;
    protected int y;
    protected final String objectToken;

    /**
     * Constructor for a default game object (including position)
     * @param x {@code int} the x position of the game object
     * @param y {@code int} the y position of the game object
     * @param objectToken {@code String} the key value of the game object in the game object hashmap
     */
    public GameObject(int x, int y, String objectToken) {
        this.x = x;
        this.y = y;
        this.objectToken = objectToken;
    }

    /**
     * Positionless constructor (used for the game object hashmap)
     * @param objectToken {@code String} the key value of the game object in the game object hashmap
     */
    public GameObject(String objectToken) {
        this.objectToken = objectToken;
    }

    /**
     * Gets the object token map
     * @return {@code HashMap<String, Tile>} object token map
     */
    public static HashMap<String, GameObject> getObjectTokenMap() {
        return OBJECT_TOKEN_MAP;
    }

    /**
     * Gets the x position of the tile
     * @return {@code int} the x position of the tile
     */
    public int getX() {
        return x;
    }

    /**
     * Updates the x position of the tile
     * @param x {@code int} the new x position of the tile
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y position of the tile
     * @return {@code int} the y position of the tile
     */
    public int getY() {
        return y;
    }

    /**
     * Updates the y position of the tile
     * @param y {@code int} the new y position of the tile
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the tile token of the tile
     * @return {@code String} the tile token of the tile
     */
    public String getObjectToken() {
        return objectToken;
    }

    public boolean isTraversable() {
        return isTraversable;
    }


    /**
     * Gets whether the tile is destructible
     * @return {@code true}: if the tile is destructible <br>
     * {@code false}: otherwise
     */
    public boolean isDestructible() {
        return isDestructible;
    }

    /**
     * Updates a tile's destructibility
     * @param destructible {@code boolean} the new destructibility of the tile
     */
    public void setDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }
}
