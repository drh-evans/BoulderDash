package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Player;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Path;

/**
 * This class represents the Diamond collectable.
 * Handles the collection of Diamonds.
 * @author Rhys Evans, Nathan Welby
 * @version 1.0
 */
public class Diamond extends Entity {
    private boolean pickedUp;

    /**
     * Create a Diamond at the coordinates (x, y).
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Diamond(int x, int y) {
        super(x, y, true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    /**
     * Position-less constructor.
     */
    public Diamond() {
        super(true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    /**
     * Collects a diamond.
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
     * Method for diamonds to drop into tiles below if eligible.
     * @param gameBoard the current game board.
     * @param levelWidth the width of the level.
     * @param index the current index.
     * @param character the player itself.
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

    /**
     * Method for diamonds to roll off a tile in the correct direction if not occupied.
     * @param gameBoard the current game board.
     * @param levelWidth the width of the level.
     * @param index the current index.
     * @param character the player itself.
     */
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

        if (gameBoard[belowPos].getObjectToken().equals("B")
                || gameBoard[belowPos].getObjectToken().equals("*")
                || gameBoard[belowPos].getObjectToken().equals("W")) {
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
                    gameBoard[lowerRight].setX(lowerRight % levelWidth);
                    gameBoard[lowerRight].setY(lowerRight / levelWidth);
                    gameBoard[index] = new Path(x, y);
                }
            }
        }
    }
}