package Game_Objects.Entities;

/**
 * Class to represent fireflies.
 * @author Haoyang Qian
 * @version 1.0
 */
public class Firefly extends FlyingEnemy {

    private final String objectToken;

    /**
     * Construct the firefly.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param direction the direction in which the firefly rotates.
     */
    public Firefly(int x, int y, String direction) {

        super(x, y, "");
        this.direction = direction;

        objectToken = "F" + direction;
    }

    /**
     * Position-less constructor.
     * @param direction the direction in which the firefly rotates.
     */
    public Firefly(String direction) {
        super("");
        this.direction = direction;

        objectToken = "F" + direction;
    }

    /**
     * Get the firefly's token.
     * @return The firefly's token.
     */
    @Override
    public String getObjectToken() {
        return objectToken;
    }
}
