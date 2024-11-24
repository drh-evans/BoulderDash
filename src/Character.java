/**
 * Character class representing a player's character.
 * @author rhysevans
 */
public class Character {
    private int X;
    private int Y;

    public Character() {
        this.X = 0;
        this.Y = 0;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
