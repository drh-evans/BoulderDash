package Tiles;

public class Key extends Tile{
    private Colours colour;
    public Key(int x, int y, Colours colour) {
        super(x, y, "Key", false, true, true);
        this.colour = colour;
    }
}
