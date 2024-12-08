package Game_Objects.Entities;

/**
 * Abstract Class to represent Flying Enemies.
 * @author Haoyang Qian
 * @version 1.0
 */
public abstract class FlyingEnemy extends Entity {

    protected String direction;

    public FlyingEnemy(int x, int y, String objectToken) {
        super(x, y, true, false, 10, objectToken);
    }

    public FlyingEnemy(String objectToken) {
        super(true, false, 10, objectToken);
    }
}
