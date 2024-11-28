package Tiles;

public class Wall extends Tile{
    static final String tileType = "W";

    public Wall(int x, int y) {
        super(x, y, true, false, false);
    }
}
