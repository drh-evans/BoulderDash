package Tiles;

public class LockedDoor extends Tile {
    private Colours colour;
    static String tileType;

    public LockedDoor(int x, int y, String tileType) {
        super(x, y, false, true, true);
        switch (tileType) {
            case "RD":
                this.colour = Colours.RED;
                break;
            case "YD":
                this.colour = Colours.YELLOW;
                break;
            case "BD":
                this.colour = Colours.BLUE;
                break;
            case "GD":
                this.colour = Colours.GREEN;
        }
    }
}
