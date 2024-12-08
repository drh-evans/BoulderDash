package Game_Objects.Entities;

import Game_Objects.GameObject;
import Game_Objects.Tiles.MagicWall;
import Game_Logic.Character;
import Game_Objects.Tiles.Path;

/**
 * Class to represent Boulder entities.
 * Controls the interaction of boulders.
 * Controls the movement of boulders.
 * @author Rhys Evans, Ahsan Mian, Nathan Welby
 * @version 1.0
 */
public class Boulder extends Entity {
    // Essentials for pushing the boulder.
    private boolean isPushable;
    private static String pushDirection;

    /**
     * Creates a Boulder at position (x, y).
     *
     * @param x the new x.
     * @param y the new y.
     */
    public Boulder(int x, int y) {
        super(x, y, true, true, 10, "B");
        this.isPushable = false;
        this.isDestructible = true;
    }

    /**
     * Creates a positionless Boulder.
     */
    public Boulder() {
        super(true, true, 10, "B");
        isPushable = true;
    }

    /**
     * Check if the Boulder is pushable.
     *
     * @param gameObjects The current tiles.
     * @param newPos      The next position.
     */
    public void pushableCheck(GameObject[] gameObjects, int newPos) {
        // check if the tile is pushable
        if (!gameObjects[newPos].getObjectToken().equals("P")) {
            this.isPushable = false;
        } else {
            this.isPushable = true;
        }
    }


    /**
     * Method to push the boulder.
     *
     * @return If the boulder has been pushed - true or false.
     */
    public boolean push(GameObject[] gameObjects, int tilePos, int direction) {
        int x = gameObjects[tilePos].getX();
        int y = gameObjects[tilePos].getY();
        // ive tried to implement this as straight-forward and simply as possible, please refactor as seen fit
        int newPos;
        if (direction == 1) {
            newPos = tilePos + 1;
            pushableCheck(gameObjects, newPos);
            if (this.isPushable) {
                gameObjects[newPos] = this;
                gameObjects[tilePos] = new Path(x, y);
                return true;
            }
        }
        if (direction == 2) {
            newPos = tilePos - 1;
            pushableCheck(gameObjects, newPos);
            if (this.isPushable) {
                gameObjects[newPos] = this;
                gameObjects[tilePos] = new Path(x, y);
                return true;
            }
        }
        return false;
    }

    /**
     *
     */
    @Override
    protected void removeTarget() {

    }

    /**
     *
     */
    @Override
    protected void isColliding() {

    }


    /**
     * Method controlling boulders falling.
     *
     * @param gameBoard  The current game board.
     * @param levelWidth The width of the level.
     * @param index      The current boulders index.
     * @param character  The players' character.
     */
    public void dropBoulder(GameObject[] gameBoard, int levelWidth, int index, Character character) {
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
     * Method to roll the boulder.
     *
     * @param gameBoard  The current game board.
     * @param levelWidth The width of the level.
     * @param index      The current boulders index.
     * @param character  The players' character.
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

    /**
     * Get the pushed direction.
     *
     * @return pushDirection -  "LEFT" or "RIGHT".
     */
    public String getPushDirection() {
        return pushDirection;
    }
}