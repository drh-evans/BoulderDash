package Tiles;

public class LockedDoor extends Tile{
    private Colours colour;

    public LockedDoor(int x, int y, Colours colour) {
        super(x, y, "Locked Door", false, true, true);
        this.colour = colour;
    }
}
