package Game_Objects.Tiles;

import Game_Logic.Character;
import Game_Logic.Level;
import Game_Objects.GameObject;

import java.io.FileNotFoundException;

/**
 * Class to store information regarding exit tiles in the game
 *
 * @author Adam Bartlett, Nathan Welby, Davide Belsole, Rhys Evans
 */
public class Exit extends Tile {

    /**
     * Basic constructor for constructing normal Exit objects
     *
     * @param x {@code int} x position of the exit tile
     * @param y {@code int} y position of the exit tile
     */
    public Exit(int x, int y) {
        super(x, y, false, true, true, "E");
    }

    /**
     * Positionless constructor for use in the object token hashmap
     */
    public Exit() {
        super(false, true, true, "E");
    }

    /**
     * Checks that a player is on an exit tile and meets the criteria to pass the level.
     *
     * @param currentLevel The current level the player is playing
     * @param character    The current character the player is using
     * @param width        the width of the level selected
     */
    public static boolean exitTileLogic(Level currentLevel, Character character, int width) {
        int x = character.getCharacterX();
        int y = character.getCharacterY();
        int characterPos = y * width + x;

        GameObject[] gameBoard = currentLevel.getGameBoard();

        for (GameObject object : gameBoard) {
            if (object.getObjectToken().equals("E")) {
                int tilePosition = object.getY() * width + object.getX();
                if (characterPos == tilePosition && character.getDiamondsCollected() >=
                        currentLevel.getDiamondsToWin()) {
                    return true;
                }
            }
        }
        return false;
    }
}
