package Game_Objects.Tiles;

import Game_Logic.Character;
import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;

/**
 * Class to store information regarding magic wall tiles in the game
 *
 * @author Adam Bartlett, Nathan Welby
 */
public class MagicWall extends Tile {

    /**
     * Basic constructor for constructing normal MagicWall objects
     *
     * @param x {@code int} x position of the magic wall tile
     * @param y {@code int} y position of the magic wall tile
     */
    public MagicWall(int x, int y) {
        super(x, y, true, false, false, "M");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public MagicWall() {
        super(true, false, false, "M");
    }

    /**
     * If a boulder is above a magic wall they will turn into a diamond under the magic wall and likewise,
     * diamond to boulder
     *
     * @param gameBoard The array containing all the pieces
     * @param width     the width of the game board
     * @param character The current character playing
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
