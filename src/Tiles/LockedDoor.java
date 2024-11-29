package Tiles;
import java.util.HashMap;

/**
 * Class to store information regarding locked door tiles in the game
 * @author Adam Bartlett, Nathan Welby
 */
public class LockedDoor extends Tile {

    // Hashmap to map locked door colours to their matching tile token
    private static final HashMap<Colours, String> DOOR_COLOURS_MAP = new HashMap<>();
    static {
        DOOR_COLOURS_MAP.put(Colours.RED, "RD");
        DOOR_COLOURS_MAP.put(Colours.YELLOW, "YD");
        DOOR_COLOURS_MAP.put(Colours.GREEN, "GD");
        DOOR_COLOURS_MAP.put(Colours.BLUE, "BD");
    }

    // Basic attributes of the locked door
    private final String tileToken;
    private final Colours colour;

    /**
     * Basic constructor for constructing normal LockedDoor objects
     * @param x {@code int} x position of the locked door tile
     * @param y {@code int} y position of the locked door tile
     */
    public LockedDoor(int x, int y, Colours colour) {
        super(x, y, false, true, true, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        tileToken = DOOR_COLOURS_MAP.get(colour);
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public LockedDoor(Colours colour) {
        super(false, true, true, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        tileToken = DOOR_COLOURS_MAP.get(colour);
    }

    /**
     * Overritten get method for the correct tile token of the locked door
     * @return {@code String} tile token of the locked door
     */
    @Override
    public String getTileToken() {
        return tileToken;
    }

    /**
     * Gets the colour of the locked door
     * @return {@code Colours} locked door colour
     */
    public Colours getColour() {
        return colour;
    }
}
