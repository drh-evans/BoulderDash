package Entities;

public class Diamond extends Entity{
    private boolean pickedUp;

    public Diamond() {
        this.canFall = false;
        this.isPassive = true;
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
