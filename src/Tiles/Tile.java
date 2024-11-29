package Tiles;
import java.util.HashMap;

/**
 * Tile class to store information regarding tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public abstract class Tile {

    // Basic attributes for each tile
    protected int x;
    protected int y;
    private final String tileToken;
    protected boolean isDestructible;
    protected boolean isTraversable;
    private boolean isTraversableEnemies;

    // Hashmap to map tile tokens to their respective tile subclass
    // NOTE: called OBJECT_TOKEN_MAP since it may be used with entities as well
    private static final HashMap<String, Tile> OBJECT_TOKEN_MAP = new HashMap<>();
    static {
        getObjectTokenMap().put("P", new Path());
        getObjectTokenMap().put("D", new Dirt());
        getObjectTokenMap().put("E", new Exit());
        getObjectTokenMap().put("RK", new Key(Colours.RED));
        getObjectTokenMap().put("YK", new Key(Colours.YELLOW));
        getObjectTokenMap().put("GK", new Key(Colours.GREEN));
        getObjectTokenMap().put("BK", new Key(Colours.BLUE));
        getObjectTokenMap().put("RD", new LockedDoor(Colours.RED));
        getObjectTokenMap().put("YD", new LockedDoor(Colours.YELLOW));
        getObjectTokenMap().put("GD", new LockedDoor(Colours.GREEN));
        getObjectTokenMap().put("BD", new LockedDoor(Colours.BLUE));
        getObjectTokenMap().put("W", new Wall());
        getObjectTokenMap().put("M", new MagicWall());
        getObjectTokenMap().put("T", new TitaniumWall());
    }

    /**
     * Default constructor for constructing normal Tile objects
     * @param x {@code int} x position of the tile
     * @param y {@code int} y position of the tile
     * @param isDestructible {@code boolean} is the tile destructible
     * @param isTraversable {@code boolean} is the tile traversible by the player
     * @param isTraversableEnemies {@code boolean} is the tile traversible by enemies
     * @param tileToken {@code String} token for the tile type
     */
    public Tile (int x, int y, boolean isDestructible, boolean isTraversable, boolean isTraversableEnemies, String tileToken) {
        this.x = x;
        this.y = y;
        this.isDestructible = isDestructible;
        this.isTraversable = isTraversable;
        this.setTraversableEnemies(isTraversableEnemies);
        this.tileToken = tileToken;
    }

    /**
     * Positionless constructor for use in the object token hashmap
     * (positions are set after initialisation in Level)
     * @param isDestructible {@code boolean} is the tile destructible
     * @param isTraversable {@code boolean} is the tile traversible by the player
     * @param isTraversableEnemies {@code boolean} is the tile traversible by enemies
     * @param tileToken {@code String} token for the tile type
     */
    public Tile(boolean isDestructible, boolean isTraversable, boolean isTraversableEnemies, String tileToken) {
        this.isDestructible = isDestructible;
        this.isTraversable = isTraversable;
        this.setTraversableEnemies(isTraversableEnemies);
        this.tileToken = tileToken;
    }

    /**
     * Gets the object token map
     * @return {@code HashMap<String, Tile>} object token map
     */
    public static HashMap<String, Tile> getObjectTokenMap() {
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

    /**
     * Gets whether the tile is traversible by the player
     * @return {@code true}: if the tile is traversible by the player <br>
     * {@code false}: otherwise
     */
    public boolean isTraversable() {
        return isTraversable;
    }

    /**
     * Updates a tile's traversibility by the player
     * @param traversable {@code boolean} the new traversibility of the tile by the player
     */
    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }

    /**
     * Gets whether the tile is traversible by enemies
     * @return {@code true}: if the tile is traversible by enemies <br>
     * {@code false}: otherwise
     */
    public boolean isTraversableEnemies() {
        return isTraversableEnemies;
    }

    /**
     * Updates a tile's traversibility by enemies
     * @param traversableEnemies {@code boolean} the new traversibility of the tile by enemies
     */
    public void setTraversableEnemies(boolean traversableEnemies) {
        isTraversableEnemies = traversableEnemies;
    }

    /**
     * Gets the tile token of the tile
     * @return {@code String} the tile token of the tile
     */
    public String getTileToken() {
        return tileToken;
    }
}


