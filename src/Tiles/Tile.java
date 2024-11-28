package Tiles;

/**
 * @author Adam Bartlett
 */
public abstract class Tile {

    protected int x;
    protected int y;
    protected String tileType;
    protected boolean isDestructible;
    protected boolean isTraversable;
    protected boolean isTraversableEnemies;


    public Tile (int x, int y, boolean isDestructible, boolean isTraversable, boolean isTraversableEnemies) {
        this.x = x;
        this.y = y;
        this.isDestructible = isDestructible;
        this.isTraversable = isTraversable;
        this.isTraversableEnemies = isTraversableEnemies;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

    public void setDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }

}


