package Game_Logic;

import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Key;
import Game_Objects.Tiles.Path;

import java.io.FileNotFoundException;

/**
 * Character class to control the movement and representation of
 * a player's character in-game.
 *
 * @author Rhys Evans
 */
public class Character {
    private final GameObject[] gameBoard;
    private int x;
    private int y;
    private int score;
    private int diamondsCollected = 0;
    private Level currentLevel;
    private Player currentPlayer;

    /**
     * Creates a character to be spawned on the map.
     */
    public Character(Level level, Player player) {
        this.x = 0;
        this.y = 0;
        this.currentLevel = level;
        this.gameBoard = currentLevel.getGameBoard();
        this.currentPlayer = player;
    }

    /**
     * Controls the characters movement based off of keyboard input.
     * Checks if the players move is within the grid limits.
     *
     * @param keyPressed key inputted by player.
     * @param gridWidth  the width of the game grid.
     * @param gridHeight the height of the game grid.
     */
    public void moveCharacter(String keyPressed, int gridWidth, int gridHeight) {
        switch (keyPressed) {
            case "RIGHT":
                // Right key was pressed. So move the player right by one cell.
                x++;
                //Checks if the tile is traversable and within the board
                if (x == gridWidth || !checkTileIsTraversable(x, y, gridWidth)) {
                    x--;
                }

                checkTile(x, y, gridWidth);
                magicWall(gridWidth);


                break;
            case "LEFT":
                // Left key was pressed. So move the player right by one cell.
                x--;

                //Checks if the tile is traversable and within the board
                if (x < 0 || !checkTileIsTraversable(x, y, gridWidth)) {
                    x++;
                }

                checkTile(x, y, gridWidth);
                magicWall(gridWidth);

                break;
            case "DOWN":
                // Down key was pressed. So move the player right by one cell.
                y++;

                //Checks if the tile is traversable and within the board
                if (y == gridHeight || !checkTileIsTraversable(x, y, gridWidth)) {
                    y--;
                }


                checkTile(x, y, gridWidth);
                magicWall(gridWidth);

                break;
            case "UP":
                // Up key was pressed. So move the player right by one cell.
                y--;

                //Checks if the tile is traversable and within the board
                if (y < 0 || !checkTileIsTraversable(x, y, gridWidth)) {
                    y++;
                }
                // check if the tile is dirt
                checkTile(x, y, gridWidth);
                magicWall(gridWidth);
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
    }

    /**
     * Checks if the new tile is a dirt tile, if so changes to path.
     *
     * @param x     the prospective x coordinate.
     * @param y     the prospective y coordinate.
     * @param width the grid's width.
     */
    private void checkTile(int x, int y, int width) {
        // The position of the tile.
        int tilePos = y * width + x;
        // If the tile is a dirt tile.
        if (checkDirtTile(tilePos)) {
            // Convert the tile to a path.
            transformToPath(tilePos);
        } else if (checkExitTile(tilePos)) {
            System.out.println("GAME OVER");
            loadNextLevel();
        } else if (checkDiamondTile(tilePos)) {
            Diamond diamond = (Diamond) gameBoard[tilePos];
            diamond.collectDiamond(this);
            transformToPath(tilePos);
        } else if (checkKeyTile(tilePos)) {
            Key key = (Key) gameBoard[tilePos];
            key.collectKey(currentPlayer);
            transformToPath(tilePos);
        }
    }

    // TODO FIX BUG WITH LEVEL LOAD

    /**
     * Load the next level upon contact with an exit tile.
     */
    private void loadNextLevel() {
        try {
            if (diamondsCollected >= currentLevel.getDiamondsToWin()) {
                // Loads the next level
                currentLevel.loadLevel("src/LevelFiles/Level2.txt");
                // Resets the characters position for next level
                x = 0;
                y = 0;
                currentPlayer.progressLevel();
                System.out.println("Loading next Level");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No level found");
        }
    }


    // TODO ADD TILE CHECK BEFORE INSTANTIATION

    /**
     * Checks that the next tile is a traversable tile.
     *
     * @param x     the new player x.
     * @param y     the new player y.
     * @param width the grid width.
     * @return boolean, if the tile is traversable.
     */
    private boolean checkTileIsTraversable(int x, int y, int width) {
        int tilePos = y * width + x;

        // Creates a tile object so isTraversable() can be used
        GameObject tile = gameBoard[tilePos];
        return tile.isTraversable();
    }

    /**
     * Check whether the tile is a dirt tile.
     *
     * @param tilePos the next tile position.
     * @return a boolean as to the status of the tile.
     */
    private boolean checkDirtTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("D") && (gameBoard[tilePos] != null);
    }

    /**
     * Check whether the tile is an exit tile.
     *
     * @param tilePos the next tile position.
     * @return a boolean as to the status of the tile.
     */
    private boolean checkExitTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("E") && (gameBoard[tilePos] != null);
    }

    /**
     * Check whether the tile is a diamond.
     *
     * @param tilePos the next tile position.
     * @return a boolean as to the status of the tile.
     */
    private boolean checkDiamondTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("*") && (gameBoard[tilePos] != null);
    }

    private boolean checkBoulderTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("B") && (gameBoard[tilePos] != null);
    }

    private boolean checkMagicWallTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("M") && (gameBoard[tilePos] != null);
    }

    /**
     * Checks if the tile is a key.
     * @param tilePos the tiles position.
     * @return keyStatus, a boolean whether it is a key or not.
     */
    private boolean checkKeyTile(int tilePos) {
        String token = gameBoard[tilePos].getObjectToken();

        return (token.equals("GK") || token.equals("RK") || token.equals("YK")
                || token.equals("BK")) && (gameBoard[tilePos] != null);
    }


    // TODO ADD TILE CHECK BEFORE INSTANTIATION
    private void transformToPath(int tilePos) {
        // Creates a tile object so isDestructible() can be used
        GameObject tile = gameBoard[tilePos];
        // check if the tile is destructible.
        if (tile.isDestructible()) {
            // turn the tile to path.
            gameBoard[tilePos] = new Path(x, y);
        }
    }


    /**
     * Getter for the characters X coordinate.
     *
     * @return X the characters X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for the characters X coordinate.
     *
     * @param x the characters new X coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for the characters Y coordinate.
     *
     * @return Y the characters Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the characters Y coordinate.
     *
     * @param y the characters new Y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the score obtained by the player.
     *
     * @return score, the score obtained by a player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score obtained by a player.
     *
     * @param newScore the new score obtained.
     */
    public void setScore(int newScore) {
        score = newScore;
    }

    /**
     * Get the amount of diamonds collected by a player.
     *
     * @return diamonds collected - the amount of diamonds collected.
     */
    public int getDiamondsCollected() {
        return diamondsCollected;
    }

    /**
     * Increment the diamonds collected.
     */
    public void setDiamondsCollected() {
        diamondsCollected++;
    }


    private void magicWall(int width) {
        for (int i = 0; i < gameBoard.length; i++) {

            if (gameBoard[i].getObjectToken().equals("M")) {
                int x = gameBoard[i].getX();
                int y = gameBoard[i].getY();

                y--;
                int tilePos = y * width + x;
                if (checkBoulderTile(tilePos)) {
                    transformToPath(tilePos);

                    int diamondPos = (y + 2) * width + x;
                    gameBoard[diamondPos] = new Diamond(this.x, this.y);
                }
                if (checkDiamondTile(tilePos)) {
                    transformToPath(tilePos);
                    int boulderPos = (y + 2) * width + x;
                    gameBoard[boulderPos] = new Boulder(this.x, this.y);
                }
            }
        }
    }
}

