package Game_Objects.Entities;

public class Firefly extends FlyingEnemy {

    private final String objectToken;

    public Firefly(int x, int y, String direction) {

        super(x, y, "");
        this.direction = direction;

        objectToken = "F" + direction;
    }

    public Firefly(String direction) {
        super("");
        this.direction = direction;

        objectToken = "F" + direction;
    }

    /**
     *
     */
    @Override
    public void removeTarget() {

    }

    /**
     *
     */
    @Override
    public void isColliding() {

    }


    @Override
    public String getObjectToken() {
        return objectToken;
    }
}
