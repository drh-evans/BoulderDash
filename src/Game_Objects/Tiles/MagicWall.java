package Game_Objects.Tiles;

import Game_Logic.Character;
import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;

/**
 * Class representing the Magic Wall.
 * Controls the Magic Walls' functionality.
 * @author Adam Bartlett, Nathan Welby
 */
public class MagicWall extends Tile {

    /**
     * Construct a Magic Wall at position (x, y).
     * @param x {@code int} x position of the magic wall tile
     * @param y {@code int} y position of the magic wall tile
     */
    public MagicWall(int x, int y) {
        super(x, y, true, false, false, "M");
    }

    /**
     * Create a positionless Magic Wall.
     */
    public MagicWall() {
        super(true, false, false, "M");
    }

    /**
     * Control the logic of the magic wall, moving valid tiles below it.
     * @param gameBoard All the current tiles.
     * @param width     The width of the board.
     * @param character The current character.
     */
    public static void magicWallLogic(GameObject[] gameBoard, int width, Character character) {
        for (int i = 0; i < gameBoard.length; i++) {
            // Loops though the game board finding magic walls
            if (gameBoard[i].getObjectToken().equals("M")) {
                int x = gameBoard[i].getX();
                int y = gameBoard[i].getY();

                y--;
                int tilePos = y * width + x;
                int nextPosition = ((y + 2) * width + x);

                // Get the (x, y) coordinates of the tile below.
                int nextX = gameBoard[nextPosition].getX();
                int nextY = gameBoard[nextPosition].getY();

                // Check if the player occupies the tile below.
                if (nextX != character.getCharacterX() && nextY != character.getCharacterY()) {
                    //Checks if a boulder or diamond is above the wall.
                    if (gameBoard[tilePos].getObjectToken().equals("B")
                            && (gameBoard[tilePos] != null)) {
                        if (gameBoard[nextPosition].getObjectToken().equals("P")) {
                            gameBoard[tilePos] = new Path(x, y);
                            gameBoard[nextPosition] = new Diamond(x, y);
                        }
                    } else if (gameBoard[tilePos].getObjectToken().equals("*")
                            && (gameBoard[tilePos] != null)) {
                        if (gameBoard[nextPosition].getObjectToken().equals("P")) {
                            gameBoard[tilePos] = new Path(x, y);
                            gameBoard[nextPosition] = new Boulder(x, y);
                        }
                    }
                }
            }
        }
    }
}
