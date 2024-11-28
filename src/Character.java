import javafx.scene.input.KeyEvent;

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

    /**
     * Controls character movement.
     * @param event event key inputted by player.
     * @param gridWidth the width of the game grid.
     * @param gridHeight the height of the game grid.
     */
    public void moveCharacter(KeyEvent event, int gridWidth, int gridHeight) {
        switch (event.getCode()) {
            case D, RIGHT:
                // Right key/D was pressed. So move the player right by one cell.
                X++;
                if (X == gridWidth) {
                    X--;
                }
                break;
            case A, LEFT:
                // Left key/A was pressed. So move the player right by one cell.
                X--;
                if (X < 0) {
                    X++;
                }
                break;
            case S, DOWN:
                // Down key/S was pressed. So move the player right by one cell.
                Y++;
                if (Y == gridHeight) {
                    Y--;
                }
                break;
            case W, UP:
                // Up key/W was pressed. So move the player right by one cell.
                Y--;
                if (Y < 0) {
                    Y++;
                }
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
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
