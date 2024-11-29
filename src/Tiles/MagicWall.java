package Tiles;

/**
 * Class to store information regarding magic wall tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class MagicWall extends Tile {

    /**
     * Basic constructor for constructing normal MagicWall objects
     * @param x {@code int} x position of the magic wall tile
     * @param y {@code int} y position of the magic wall tile
     */
    public MagicWall(int x, int y) {
        super(x, y, true, false, false, "M");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public MagicWall() {
        super(true, false, false, "M");
    }


}
