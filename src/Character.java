
/**
 * Character class to control the movement and representation of
 * a player's character in-game.
 * @author Rhys Evans
 */
public class Character {
    private int X;
    private int Y;

    /**
     * Creates a character to be spawned on the map.
     */
    public Character() {
        this.X = 0;
        this.Y = 0;
    }

    /**
     * Controls the characters movement based off of keyboard input.
     * Checks if the players move is within the grid limits.
     * @param keyPressed key inputted by player.
     * @param gridWidth the width of the game grid.
     * @param gridHeight the height of the game grid.
     */
    public void moveCharacter(String keyPressed, int gridWidth, int gridHeight) {
        switch (keyPressed) {
            case "RIGHT":
                // Right key was pressed. So move the player right by one cell.
                X++;
                if (X == gridWidth) {
                    X--;
                }
                if (Level.checkTile(X, Y, "D")) {
                    // Call method to turn dirt to path
                    Level.transformToPath(X,Y);
                    System.out.println("Test Dirt");
                }
                break;
            case "LEFT":
                // Left key was pressed. So move the player right by one cell.
                X--;
                if (X < 0) {
                    X++;
                }
                if (Level.checkTile(X, Y, "D")) {
                    // Call method to turn dirt to path
                    Level.transformToPath(X,Y);
                    System.out.println("Test Dirt");
                }
                break;
            case "DOWN":
                // Down key was pressed. So move the player right by one cell.
                Y++;
                if (Y == gridHeight) {
                    Y--;
                }
                if (Level.checkTile(X, Y, "D")) {
                    // Call method to turn dirt to path
                    Level.transformToPath(X,Y);
                    System.out.println("Test Dirt");
                }
                break;
            case "UP":
                // Up key was pressed. So move the player right by one cell.
                Y--;
                if (Y < 0) {
                    Y++;
                }
                if (Level.checkTile(X, Y, "D")) {
                    // Call method to turn dirt to path
                    Level.transformToPath(X,Y);
                    System.out.println("Test Dirt");
                }
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
    }

    /**
     * Getter for the characters X coordinate.
     * @return X the characters X coordinate.
     */
    public int getX() {
        return X;
    }

    /**
     * Setter for the characters X coordinate.
     * @param x the characters new X coordinate.
     */
    public void setX(int x) {
        X = x;
    }

    /**
     * Getter for the characters Y coordinate.
     * @return Y the characters Y coordinate.
     */
    public int getY() {
        return Y;
    }

    /**
     * Setter for the characters Y coordinate.
     * @param y the characters new Y coordinate.
     */
    public void setY(int y) {
        Y = y;
    }
}
