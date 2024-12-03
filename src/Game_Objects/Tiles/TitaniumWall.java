package Game_Objects.Tiles;

/**
 * Class to store information regarding titanium wall tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class TitaniumWall extends Tile {

    /**
     * Basic constructor for constructing normal TitaniumWall objects
     * @param x {@code int} x position of the titanium wall tile
     * @param y {@code int} y position of the titanium wall tile
     */
    public TitaniumWall(int x, int y) {
        super(x, y, false, false, false, "T");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public TitaniumWall() {
        super(false, false, false, "T");
    }
}
