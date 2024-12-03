package Game_Objects.Entities;

public class Amoeba extends Entity {

    // Basic attributes for amoebas
    private int growthRate;
    private int maxSize;
    private int currentSize;
    private boolean isMaximum;

    public Amoeba(int x, int y, int growthRate, int maxSize) {
        super(x, y, true, false, 10, "A");
        this.growthRate = growthRate;
        this.maxSize = maxSize;
        currentSize = 1;
    }

    public Amoeba() {
        super(true, false, 10, "A");
        currentSize = 1;
    }
    
    private void canGrow() {
        
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

    /**
     *
     */
    @Override
    public void dropDiamond() {

    }
}
