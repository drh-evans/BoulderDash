package Game_Objects.Tiles;

/**
 * Class to store information regarding wall tiles in the game.
 * @author Adam Bartlett, Nathan Welby
 */
public class Wall extends Tile{

    /**
     * Basic constructor for constructing normal Wall objects
     * @param x {@code int} x position of the wall tile
     * @param y {@code int} y position of the wall tile
     */
    public Wall(int x, int y) {
        super(x, y, true, false, false, "W");
    }

    /**
     * Positionless constructor for use in the object token hashmap.
     */
    public Wall() {
        super(true, false, false, "W");
    }
}
