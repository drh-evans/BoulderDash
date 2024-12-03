package Game_Objects.Tiles;

/**
 * Class to store information regarding exit tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class Exit extends Tile {

    /**
     * Basic constructor for constructing normal Exit objects
     * @param x {@code int} x position of the exit tile
     * @param y {@code int} y position of the exit tile
     */
    public Exit(int x, int y) {
        super(x, y, false, true, true, "E");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public Exit() {
        super(false, true, true, "E");
    }
}
