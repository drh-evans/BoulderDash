package Game_Objects.Entities;

/**
 * Class to represent butterflies.
 * @author Haoyang Qian
 * @version 1.0
 */
public class Butterfly extends FlyingEnemy {

    private final String objectToken;

    /**
     * Constructs the butterfly.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param direction the direction of movement.
     */
    public Butterfly(int x, int y, String direction) {
        super(x, y, "");
        this.direction = direction;

        objectToken = "B" + direction;
    }

    /**
     * Position-less constructor.
     * @param direction the direction in which the butterfly travels.
     */
    public Butterfly(String direction) {
        super("");
        this.direction = direction;

        objectToken = "B" + direction;
    }

    /**
     * Gathers token of specific object.
     * @return the token of the butterfly.
     */
    @Override
    public String getObjectToken() {
        return objectToken;
    }
}
