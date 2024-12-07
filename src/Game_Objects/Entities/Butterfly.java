package Game_Objects.Entities;

public class Butterfly extends FlyingEnemy {

    private final String objectToken;

    public Butterfly(int x, int y, String direction) {
        super(x, y, "");
        this.direction = direction;

        objectToken = "B" + direction;
    }

    public Butterfly(String direction) {
        super("");
        this.direction = direction;

        objectToken = "B" + direction;
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
