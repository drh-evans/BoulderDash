package Game_Objects.Entities;

public abstract class FlyingEnemy extends Entity {

    protected String direction;

    public FlyingEnemy(int x, int y, String objectToken) {
        super(x, y, true, false, 10, objectToken);
    }

    public FlyingEnemy(String objectToken) {
        super(true, false, 10, objectToken);
    }
}
