package Game_Objects.Tiles;

/**
 * Class to store information regarding dirt tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class Dirt extends Tile {

    /**
     * Basic constructor for constructing normal Dirt objects
     * @param x {@code int} x position of the dirt tile
     * @param y {@code int} y position of the dirt tile
     */
    public Dirt(int x, int y) {
        super(x, y, true, true, false, "D");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public Dirt(){
        super(true, true, false, "D");
    }

}
