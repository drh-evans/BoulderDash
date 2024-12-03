package Game_Objects.Entities;

public class Diamond extends Entity {
    private boolean pickedUp;

    public Diamond(int x, int y) {
        super(x, y, true, true, 10, "*");
        this.pickedUp = false;
    }

    public Diamond() {
        super(true, true, 10, "*");
        this.pickedUp = false;
    }

    /**
     *
     */
    @Override
    protected void removeTarget() {
        if (pickedUp) {

        }
    }

    /**
     *
     */
    @Override
    protected void isColliding() {

    }

    /**
     *
     */
    @Override
    protected void dropDiamond() {

    }
}
