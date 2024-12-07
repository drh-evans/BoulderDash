package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Player;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Path;

/**
 * This class represents the Diamond collectable.
 * Also handles the collection of Diamonds.
 *
 * @author Rhys Evans, Elbu Cheung.
 */
public class Diamond extends Entity {


    private boolean pickedUp;
    private boolean isFalling = false;

    /**
     * Create a Diamond at the coordinates (x, y).
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Diamond(int x, int y) {
        super(x, y, true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    public Diamond() {
        super(true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    /**
     * Collects a diamond.
     *
     * @param character The players' character.
     */
    public void collectDiamond(Character character) {
        // Get the current player profile.
        Player currentPlayer = character.getCurrentPlayer();

        // Get the previous score.
        int oldScore = currentPlayer.getLevelScore();
        int newScore = oldScore + 10;
        character.setDiamondsCollected();

        // Calculate the final score.
        currentPlayer.setLevelScore(newScore);

        pickedUp = true;
    }

    /**
     *
     */
    @Override
    protected void removeTarget() {
        if (pickedUp) {

        }
    }

    /**
     *
     */
    @Override
    protected void isColliding() {

    }

    /**
     *
     */
    public void dropDiamond(GameObject[] gameBoard, int levelWidth, int index, Character character) {
        int characterY = character.getCharacterY();
        int characterX = character.getCharacterX();

        int belowPos = index + levelWidth;
        int characterPos = (characterY * levelWidth) + characterX;
        if (gameBoard[belowPos].getObjectToken().equals("P")
                && belowPos != characterPos) {

            setFalling(true);
            gameBoard[belowPos] = this;
            gameBoard[belowPos].setX(index % levelWidth);
            gameBoard[belowPos].setY((index / levelWidth) + 1);
            gameBoard[index] = new Path(x, y);
        }
    }

    public void roll(GameObject[] gameBoard, int levelWidth, int index, Character character) {
        int belowPos = index + levelWidth;
        boolean lastRow = gameBoard[belowPos].getObjectToken().equals("T");

        int upperLeft = index - 1;
        int upperRight = index + 1;
        int lowerLeft = belowPos - 1;
        int lowerRight = belowPos + 1;

        int characterY = character.getCharacterY();
        int characterX = character.getCharacterX();
        int characterPos = (characterY * levelWidth) + characterX;

        boolean rollableLeft = gameBoard[upperLeft].getObjectToken().equals("P") &&
                gameBoard[lowerLeft].getObjectToken().equals("P") && lowerLeft != characterPos
                && upperLeft != characterPos;
        boolean rollableRight = gameBoard[upperRight].getObjectToken().equals("P") &&
                gameBoard[lowerRight].getObjectToken().equals("P") && lowerRight != characterPos
                && upperRight != characterPos;

        if (!lastRow && !gameBoard[belowPos].getObjectToken().equals("P")) {
            if (rollableLeft) {
                setFalling(true);
                gameBoard[lowerLeft] = this;
                gameBoard[lowerLeft].setX(lowerLeft % levelWidth);
                gameBoard[lowerLeft].setY(lowerLeft / levelWidth);
                gameBoard[index] = new Path(x, y);
            } else if (rollableRight) {
                setFalling(true);
                gameBoard[lowerRight] = this;
                gameBoard[lowerRight].setX(lowerLeft % levelWidth);
                gameBoard[lowerRight].setY(lowerLeft / levelWidth);
                gameBoard[index] = new Path(x, y);
            }
        }
    }
}