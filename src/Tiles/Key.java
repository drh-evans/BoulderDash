package Tiles;

public class Key extends Tile{
    private Colours colour;
    static String tileType;
    public Key(int x, int y, String tileType) {
        super(x, y, false, true, true);
        switch (tileType) {
            case "RK":
                this.colour =  Colours.RED;
                break;
            case "YK":
                this.colour =  Colours.YELLOW;
                break;
            case "BK":
                this.colour =  Colours.BLUE;
                break;
            case "GK":
                this.colour = Colours.GREEN;
        }
    }
}
