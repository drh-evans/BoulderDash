package Game_Objects.Tiles;

/**
 * Class to store information regarding titanium wall tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class TitaniumWall extends Tile {

    /**
     * Creates a Titanium Wall at (x, y).
     * @param x {@code int} x position of the titanium wall tile.
     * @param y {@code int} y position of the titanium wall tile.
     */
    public TitaniumWall(int x, int y) {
        super(x, y, false, false, false, "T");
    }

    /**
     * Creates a positionless Titanium Wall.
     */
    public TitaniumWall() {
        super(false, false, false, "T");
    }
}
