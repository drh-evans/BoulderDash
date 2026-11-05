package Game_Objects.Entities;

/**
 * Abstract Class to represent Flying Enemies.
 * @author Haoyang Qian
 * @version 1.0
 */
public abstract class FlyingEnemy extends Entity {

    protected String direction;

    /**
     * Constructs the flying enemy.
     * @param x the x coordinate of the flying enemy.
     * @param y the y coordinate of the flying enemy.
     * @param objectToken the token of the specific entity.
     */
    public FlyingEnemy(int x, int y, String objectToken) {
        super(x, y, true, false, 10, objectToken);
    }

    /**
     * Position-less constructor.
     */
    public FlyingEnemy(String objectToken) {
        super(true, false, 10, objectToken);
    }
}
