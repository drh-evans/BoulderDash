package Game_Objects.Tiles;
import java.util.HashMap;

/**
 * Class to store information regarding locked door tiles in the game
 * @author Adam Bartlett, Nathan Welby, Rhys Evans
 */
public class LockedDoor extends Tile {

    // Hashmap to store doors and their colours.
    private static final HashMap<Colours, String> DOOR_COLOURS_MAP = new HashMap<>();
    static {
        DOOR_COLOURS_MAP.put(Colours.RED, "RD");
        DOOR_COLOURS_MAP.put(Colours.YELLOW, "YD");
        DOOR_COLOURS_MAP.put(Colours.GREEN, "GD");
        DOOR_COLOURS_MAP.put(Colours.BLUE, "BD");
    }

    // Basic attributes of the locked door
    private final String objectToken;
    private final Colours colour;

    /**
     * Create a door at positions (x, y).
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param colour The colour of the door.
     */
    public LockedDoor(int x, int y, Colours colour) {
        super(x, y, true, false, false, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        objectToken = DOOR_COLOURS_MAP.get(colour);
    }


    /**
     * Construct a positionless door.
     * @param colour The doors colour.
     */
    public LockedDoor(Colours colour) {
        super(true, false, false, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        objectToken = DOOR_COLOURS_MAP.get(colour);
    }

    /**
     * Overritten get method for the correct tile token of the locked door
     * @return {@code String} tile token of the locked door
     */
    @Override
    public String getObjectToken() {
        return objectToken;
    }

    /**
     * Gets the colour of the locked door
     * @return {@code Colours} locked door colour
     */
    public Colours getColour() {
        return colour;
    }
}
