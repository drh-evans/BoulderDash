package Game_Logic;

import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Colours;
import Game_Objects.Tiles.Key;
import Game_Objects.Tiles.LockedDoor;
import Game_Objects.Tiles.Path;

/**
 * Character class to control the movement and representation of
 * a player's character in-game.
 *
 * @author Rhys Evans
 */
public class Character {
    private final GameObject[] gameBoard;
    private int characterX;
    private int characterY;
    private int tilePos = 0;
    private int diamondsCollected = 0;
    private Level currentLevel;
    private Player currentPlayer;

    /**
     * Creates a character to be spawned on the map.
     */
    public Character(Level level, Player player) {
        this.characterX = 1;
        this.characterY = 1;
        this.currentLevel = level;
        this.gameBoard = currentLevel.getGameBoard();
        this.currentPlayer = player;
    }

    /**
     * Controls the characters movement.
     *
     * @param keyPressed The key inputted by player.
     * @param gridWidth  The width of the game grid.
     * @param gridHeight The height of the game grid.
     */
    public void moveCharacter(String keyPressed, int gridWidth, int gridHeight) {
        System.out.println("Current Key Pressed:" + keyPressed);
        switch (keyPressed) {
            case "RIGHT":
                characterX++;

                checkTile(characterX, characterY, gridWidth, 1);

                //Checks if the tile is traversable and within the board
                if (characterX == gridWidth || !checkTileIsTraversable(characterX, characterY, gridWidth)) {
                    characterX--;
                }
                break;
            case "LEFT":
                // Left key was pressed. So move the player right by one cell.
                characterX--;

                checkTile(characterX, characterY, gridWidth, 2);

                //Checks if the tile is traversable and within the board
                if (characterX < 0 || !checkTileIsTraversable(characterX, characterY, gridWidth)) {
                    characterX++;
                }
                break;
            case "DOWN":
                // Down key was pressed. So move the player right by one cell.
                characterY++;

                checkTile(characterX, characterY, gridWidth, 3);
                //Checks if the tile is traversable and within the board
                if (characterY == gridHeight || !checkTileIsTraversable(characterX, characterY, gridWidth)) {
                    characterY--;
                }
                break;
            case "UP":
                // Up key was pressed. So move the player right by one cell.
                characterY--;

                checkTile(characterX, characterY, gridWidth, 4);
                //Checks if the tile is traversable and within the board
                if (characterY < 0 || !checkTileIsTraversable(characterX, characterY, gridWidth)) {
                    characterY++;
                }
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
    private void checkTile(int x, int y, int width, int direction) {
        // The position of the tile.
        int tilePos = y * width + x;
        // If the tile is a dirt tile.
        if (checkDirtTile(tilePos)) {
            // Convert the tile to a path.
            transformToPath(tilePos);
        } else if (checkExitTile(tilePos)) {
            if (diamondsCollected > currentLevel.getDiamondsToWin()) {
                System.out.println("LEVEL COMPLETE!");
                currentPlayer.setFinalLevelScore(currentLevel);
                System.out.println("FINAL SCORE: " + currentPlayer.getFinalLevelScore());
            }
        } else if (checkDiamondTile(tilePos)) {
            Diamond diamond = (Diamond) gameBoard[tilePos];
            diamond.collectDiamond(this);
            transformToPath(tilePos);
        } else if (checkBoulderTile(tilePos)) {
            Boulder boulder = (Boulder) gameBoard[tilePos];
            if (!boulder.push(gameBoard, tilePos, direction)) {
                if (direction == 1) {
                    tilePos--;
                } else if (direction == 2) {
                    tilePos++;
                }
            }
            if (direction == 1 || direction == 2) {
                transformToPath(tilePos);
            }
        } else if (checkKeyTile(tilePos)) {
            Key key = (Key) gameBoard[tilePos];
            key.collectKey(currentPlayer);
            transformToPath(tilePos);
        } else if (checkDoorTile(tilePos)) {
            LockedDoor door = (LockedDoor) gameBoard[tilePos];
            // Get the current doors colour.
            Colours doorColour = door.getColour();
            System.out.println(door.getColour());
            // Check if the player has the current doors key.
            boolean hasKey = currentPlayer.checkInventory(doorColour);
            System.out.println(hasKey);
            // Open the door.
            if (hasKey) {
                door.setTraversable(true);
                transformToPath(tilePos);
                currentPlayer.removeKey(doorColour);
            }
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
        return gameBoard[tilePos].getObjectToken().equals("D")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Check whether the tile is an exit tile.
     *
     * @param tilePos the next tile position.
     * @return a boolean as to the status of the tile.
     */
    private boolean checkExitTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("E")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Check whether the tile is a diamond.
     *
     * @param tilePos the next tile position.
     * @return a boolean as to the status of the tile.
     */
    private boolean checkDiamondTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("*")
                && (gameBoard[tilePos] != null);
    }

    private boolean checkBoulderTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("B")
                && (gameBoard[tilePos] != null);
    }

    private boolean checkMagicWallTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("M")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Checks if the tile is a key.
     *
     * @param tilePos the tiles position.
     * @return keyStatus, a boolean whether it is a key or not.
     */
    private boolean checkKeyTile(int tilePos) {
        String token = gameBoard[tilePos].getObjectToken();
        return (token.equals("GK") || token.equals("RK") || token.equals("YK")
                || token.equals("BK")) && (gameBoard[tilePos] != null);
    }

    /**
     * Checks if a tile is a door.
     *
     * @param tilePos the tiles position.
     * @return doorStatus, whether it is a door.
     */
    private boolean checkDoorTile(int tilePos) {
        String token = gameBoard[tilePos].getObjectToken();
        return (token.equals("GD") || token.equals("RD") || token.equals("YD")
                || token.equals("BD")) && (gameBoard[tilePos] != null);
    }


    // TODO ADD TILE CHECK BEFORE INSTANTIATION
    private void transformToPath(int tilePos) {
        // Creates a tile object so isDestructible() can be used
        GameObject tile = gameBoard[tilePos];
        // check if the tile is destructible.
        if (tile.isDestructible()) {
            // turn the tile to path.
            gameBoard[tilePos] = new Path(characterX, characterY);
        }
    }

    /**
     * Resets the characters position to the starting position.
     */
    public void resetCharacterPosition() {
        this.characterX = 1;
        this.characterY = 1;
    }


    /**
     * Getter for the characters X coordinate.
     *
     * @return X the characters X coordinate.
     */
    public int getCharacterX() {
        return characterX;
    }

    /**
     * Setter for the characters X coordinate.
     *
     * @param characterX the characters new X coordinate.
     */
    public void setCharacterX(int characterX) {
        this.characterX = characterX;
    }

    /**
     * Getter for the characters Y coordinate.
     *
     * @return Y the characters Y coordinate.
     */
    public int getCharacterY() {
        return characterY;
    }

    /**
     * Setter for the characters Y coordinate.
     *
     * @param characterY the characters new Y coordinate.
     */
    public void setCharacterY(int characterY) {
        this.characterY = characterY;
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

    /**
     * Get the active player.
     *
     * @return a Player, the current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

