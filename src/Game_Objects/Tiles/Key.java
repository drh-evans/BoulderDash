package Game_Objects.Tiles;
import Game_Logic.Character;
import Game_Logic.Player;

import java.util.HashMap;

/**
 * Class to store information regarding key tiles in the game
 * @author Adam Bartlett, Nathan Welby, Rhys Evans
 */
public class Key extends Tile {

    private boolean isCollected;

    // Hashmap to map key colours to their matching tile token
    private static final HashMap<Colours, String> KEY_COLOURS_MAP = new HashMap<>();
    static {
        KEY_COLOURS_MAP.put(Colours.RED, "RK");
        KEY_COLOURS_MAP.put(Colours.YELLOW, "YK");
        KEY_COLOURS_MAP.put(Colours.GREEN, "GK");
        KEY_COLOURS_MAP.put(Colours.BLUE, "BK");
    }

    // Basic attributes of the key
    private final String objectToken;
    private final Colours colour;

    /**
     * Basic constructor for constructing normal Key objects
     * @param x {@code int} x position of the key tile
     * @param y {@code int} y position of the key tile
     */
    public Key(int x, int y, Colours colour) {
        super(x, y, true, true, true, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        objectToken = KEY_COLOURS_MAP.get(colour);
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public Key(Colours colour) {
        super(false, true, true, "");
        this.colour = colour;

        // Override the previous tile token initialisation in the superclass constructor
        objectToken = KEY_COLOURS_MAP.get(colour);
    }

    public void collectKey(Player player) {
        isCollected = true;
        player.addToInventory(this);
    }

    /**
     * Overritten get method for the correct tile token of the key
     * @return {@code String} tile token of the key
     */
    @Override
    public String getObjectToken() {
        return objectToken;
    }

    /**
     * Gets the colour of the key
     * @return {@code Colours} key colour
     */
    public Colours getColour() {
        return colour;
    }
}
