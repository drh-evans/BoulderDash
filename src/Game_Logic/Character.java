package Game_Logic;

import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Colours;
import Game_Objects.Tiles.Key;
import Game_Objects.Tiles.LockedDoor;
import Game_Objects.Tiles.Path;

/**
 * Character class representing a player in-game.
 * Controls the characters movement.
 * Controls the characters collisions.
 * @author Rhys Evans, Davide Belsole, Adam Bartlett
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
     * Create a new character.
     * @param level The current level.
     * @param player The current player.
     */
    public Character(Level level, Player player) {
        this.characterX = 1;
        this.characterY = 1;
        this.currentLevel = level;
        this.gameBoard = currentLevel.getGameBoard();
        this.currentPlayer = player;
    }

    /**
     * Control the characters' movement.
     * @param keyPressed The key inputted by player.
     * @param gridWidth  The width of the game grid.
     * @param gridHeight The height of the game grid.
     */
    public void moveCharacter(String keyPressed, int gridWidth, int gridHeight) {
        System.out.println("CURRENT KEY PRESSED: " + keyPressed);
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
     * Check the next path prior to moving.
     * @param x     The next x coordinate.
     * @param y     The next y coordinate.
     * @param width The grid's width.
     */
    private void checkTile(int x, int y, int width, int direction) {
        // The position of the new tile.
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

            // Check if the player has the current doors key.
            boolean hasKey = currentPlayer.checkInventory(doorColour);
            System.out.println("OPEN: " + hasKey);
            // Open the door.
            if (hasKey) {
                door.setTraversable(true);
                transformToPath(tilePos);
                currentPlayer.removeKey(doorColour);
            }
        }
    }

    /**
     * Checks that the next tile is traversable.
     * @param x     The new player x.
     * @param y     The new player y.
     * @param width The grid width.
     * @return boolean, if the tile is traversable true or false.
     */
    private boolean checkTileIsTraversable(int x, int y, int width) {
        int tilePos = y * width + x;

        // Creates a tile object so isTraversable() can be used
        GameObject tile = gameBoard[tilePos];
        return tile.isTraversable();
    }

    /**
     * Check if the tile is dirt.
     * @param tilePos The next tile position.
     * @return boolean, true or false.
     */
    private boolean checkDirtTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("D")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Check if the tile is an exit tile.
     * @param tilePos The next tile position.
     * @return a boolean as to the status of the tile, true or false.
     */
    private boolean checkExitTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("E")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Check if the tile is a diamond.
     * @param tilePos The next tile position.
     * @return boolean as to the status of the tile, true or false.
     */
    private boolean checkDiamondTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("*")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Check if the tile is a diamond.
     * @param tilePos The next tile position.
     * @return boolean as to the status of the tile, true or false.
     */
    private boolean checkBoulderTile(int tilePos) {
        return gameBoard[tilePos].getObjectToken().equals("B")
                && (gameBoard[tilePos] != null);
    }

    /**
     * Checks if the tile is a key.
     * @param tilePos The tiles position.
     * @return keyStatus, a boolean whether it is a key or not.
     */
    private boolean checkKeyTile(int tilePos) {
        String token = gameBoard[tilePos].getObjectToken();
        return (token.equals("GK") || token.equals("RK") || token.equals("YK")
                || token.equals("BK")) && (gameBoard[tilePos] != null);
    }

    /**
     * Checks if a tile is a door.
     * @param tilePos the tiles position.
     * @return doorStatus, true or false.
     */
    private boolean checkDoorTile(int tilePos) {
        String token = gameBoard[tilePos].getObjectToken();
        return (token.equals("GD") || token.equals("RD") || token.equals("YD")
                || token.equals("BD")) && (gameBoard[tilePos] != null);
    }


    /**
     * Converts a tile to a path.
     * @param tilePos The tiles position.
     */
    private void transformToPath(int tilePos) {
        // Creates a tile object so isDestructible() can be used
        GameObject tile = gameBoard[tilePos];
        // Check if the tile is destructible.
        if (tile.isDestructible()) {
            // Turn the tile to path.
            gameBoard[tilePos] = new Path(characterX, characterY);
        }
    }


    /**
     * Resets the characters position.
     */
    public void resetCharacterPosition() {
        this.characterX = 1;
        this.characterY = 1;
    }


    /**
     * Get the characters X coordinate.
     * @return X, the characters X coordinate.
     */
    public int getCharacterX() {
        return characterX;
    }

    /**
     * Set the characters X coordinate.
     * @param characterX The characters new X coordinate.
     */
    public void setCharacterX(int characterX) {
        this.characterX = characterX;
    }

    /**
     * Get the characters Y coordinate.
     * @return Y, the characters Y coordinate.
     */
    public int getCharacterY() {
        return characterY;
    }

    /**
     * Set the characters Y coordinate.
     * @param characterY the characters new Y coordinate.
     */
    public void setCharacterY(int characterY) {
        this.characterY = characterY;
    }

    /**
     * Get the amount of diamonds collected by a player.
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
     * @return currentPlayer, a Player object.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

