package Game_Objects.Tiles;

/**
 * Class to store information regarding path tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class Path extends Tile {

    /**
     * Creates a Path at position (x, y).
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Path(int x, int y) {
        super(x, y, false,true,true, "P");
    }

    /**
     * Creates a path without a position.
     */
    public Path() {
        super(false,true,true, "P");
    }
}
