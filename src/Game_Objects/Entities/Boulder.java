package Game_Objects.Entities;

import Game_Objects.GameObject;
import Game_Objects.Tiles.MagicWall;
import Game_Logic.Character;
import Game_Objects.Tiles.Path;

/**
 * Class to represent Boulder entities.
 * @author Rhys Evans, Ahsan Mian, Nathan Welby
 */
public class Boulder extends Entity {
    private boolean isPushable;
    private static String pushDirection;

    /**
     * Constructor for instanstienising a boulder.
     */
    public Boulder() {
        super(true, true, 10, "B");
        isPushable = true;
    }

    /**
     * Constructor for making a new boulder i.e moving it.
     *
     * @param x the new x.
     * @param y the new y.
     */
    public Boulder(int x, int y) {
        super(x, y, true, true, 10, "B");
        this.isPushable = false;
        this.isDestructible = true;
    }

    public void pushableCheck(GameObject[] gameObjects, int newPos) {
        // check if the tile is pushable
        if (!gameObjects[newPos].getObjectToken().equals("P")) {
            this.isPushable = false;
        } else {
            this.isPushable = true;
        }
    }


    /**
     * Method to push a boulder if it is pushable.
     *
     * @return
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
     *
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
                    gameBoard[lowerRight].setX(lowerLeft % levelWidth);
                    gameBoard[lowerRight].setY(lowerLeft / levelWidth);
                    gameBoard[index] = new Path(x, y);
                }
            }
        }
    }

    public String getPushDirection() {
        return pushDirection;
    }
}