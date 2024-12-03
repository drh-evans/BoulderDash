package Game_Objects.Tiles;
import Game_Objects.GameObject;

/**
 * Tile class to store information regarding tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public abstract class Tile extends GameObject {

    // Basic attributes for each tile
    private boolean isTraversableEnemies;

    /**
     * Default constructor for constructing normal Tile objects
     * @param x {@code int} x position of the tile
     * @param y {@code int} y position of the tile
     * @param isDestructible {@code boolean} is the tile destructible
     * @param isTraversable {@code boolean} is the tile traversible by the player
     * @param isTraversableEnemies {@code boolean} is the tile traversible by enemies
     * @param objectToken {@code String} token for the tile type
     */
    public Tile(int x, int y, boolean isDestructible, boolean isTraversable, boolean isTraversableEnemies, String objectToken) {
        super(x, y, objectToken);
        this.isDestructible = isDestructible;
        this.isTraversable = isTraversable;
        this.setTraversableEnemies(isTraversableEnemies);
    }

    /**
     * Positionless constructor for use in the object token hashmap
     * (positions are set after initialisation in Level)
     * @param isDestructible {@code boolean} is the tile destructible
     * @param isTraversable {@code boolean} is the tile traversible by the player
     * @param isTraversableEnemies {@code boolean} is the tile traversible by enemies
     * @param objectToken {@code String} token for the tile type
     */
    public Tile(boolean isDestructible, boolean isTraversable, boolean isTraversableEnemies, String objectToken) {
        super(objectToken);
        this.isDestructible = isDestructible;
        this.isTraversable = isTraversable;
        this.setTraversableEnemies(isTraversableEnemies);
    }

    /*
    public static GameObject changeTile(GameObject tile, String newTileToken) {
        int xPos = tile.getX();
        int yPos = tile.getY();

        tile = GameObject.getObjectTokenMap().get(newTileToken);

        tile.setX(xPos);
        tile.setY(yPos);

        return tile;
    }
    */

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
}


