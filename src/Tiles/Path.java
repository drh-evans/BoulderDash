package Tiles;

/**
 * Class to store information regarding path tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class Path extends Tile {

    /**
     * Basic constructor for constructing normal Path objects
     * @param x {@code int} x position of the path tile
     * @param y {@code int} y position of the path tile
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
