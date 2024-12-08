package Game_Objects.Tiles;

/**
 * Class to store information regarding path tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class Path extends Tile {

    /**
     * Creates a Path at (
     * @param x
     * @param y
     */
    public Path(int x, int y) {
        super(x, y, false,true,true, "P");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public Path() {
        super(false,true,true, "P");
    }
}
