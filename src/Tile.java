public abstract class Tile {

    protected int x;
    protected int y;
    protected String tileType;
    protected boolean isDestructable;
    protected boolean isTraversable;

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

    public boolean isDestructable() {
        return isDestructable;
    }

    public void setDestructable(boolean destructable) {
        isDestructable = destructable;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }
}
