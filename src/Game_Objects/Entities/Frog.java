package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Level;
import Game_Objects.GameObject;

import java.util.Arrays;

/**
  * Frog class represent frog entity.
  * @author Davide Belsole, Elbu Cheung.
*/
public class Frog extends Entity {
    private int playerPositionX;
    private int playerPositionY;

    /**
     * Constructs the frog.
     * @param x the x coordinate of the frog.
     * @param y the y coordinate of the frog.
     */
    public Frog(int x, int y) {
        super(x, y, false, false, 10, "F");

    }

    /**
     * Position-less constructor.
     */
    public Frog() {
        super(false, false, 10, "F");
    }

    /**
     * Validates the frogs movement so that it does not traverse out of bounds or onto tiles that are not valid.
     *
     * @param x     the x coordinate.
     * @param y     the y coordinate.
     * @param level The level of the game.
     * @return false if the tile is not in bounds or is not a valid tile, return true if valid.
     */
    private boolean valid(int x, int y, Level level) {
        int width = level.getWidth();
        int height = level.getHeight();

        // Checks that the position is within bounds and valid
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }

        int frogPos = y * width + x;

        // Check if the tile is traversable
        return level.getGameBoard()[frogPos].isTraversable();
    }

    /**
     * Method to gather the current position of the character, so that the frog can move towards it based.
       on the shortest path, moving both horizontally and vertically.
     * @param level the level of the game.
     * @param character the player that will be chased by the frog.
     */
    public void chasePlayer(Level level, Character character) {
        int posX = this.getX();
        int posY = this.getY();

        // Gather the position of the character
        getPlayerPosition(character);

        // Movement of the frog based on the current position of the player, including validity check
        if (posX < playerPositionX && valid(posX + 1, posY, level)) {
            setX(posX + 1);
        } else if (posX > playerPositionX && valid(posX - 1, posY, level)) {
            setX(posX - 1);
        } else if (posY < playerPositionY && valid(posX, posY + 1, level)) {
            setY(posY + 1);
        } else if (posY > playerPositionY && valid(posX, posY - 1, level)) {
            setY(posY - 1);
        }
    }

    /**
     * Gathers the position of the character.
     * @param character the character.
     */
    private void getPlayerPosition(Character character) {
        playerPositionX = character.getCharacterX();
        playerPositionY = character.getCharacterY();
    }
}
