import Tiles.Path;
import Tiles.Tile;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

/**
 * Character class to control the movement and representation of
 * a player's character in-game.
 * @author Rhys Evans
 */
public class Character {
    private int x;
    private int y;
    private int diamondsCollected;
    private Level currentLevel;
    private Tile[] tiles;

    /**
     * Creates a character to be spawned on the map.
     */
    public Character(Level level) {
        this.x = 0;
        this.y = 0;
        this.currentLevel = level;
        this.tiles = currentLevel.getTiles();
    }

    /**
     * Controls the characters movement based off of keyboard input.
     * Checks if the players move is within the grid limits.
     * @param keyPressed key inputted by player.
     * @param gridWidth the width of the game grid.
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

                checkTile(x, y,gridWidth);

                break;
            case "LEFT":
                // Left key was pressed. So move the player right by one cell.
                x--;

                //Checks if the tile is traversable and within the board
                if (x < 0 || !checkTileIsTraversable(x, y, gridWidth)) {
                x++;
                }

                checkTile(x, y,gridWidth);

                break;
            case "DOWN":
                // Down key was pressed. So move the player right by one cell.
                y++;

                //Checks if the tile is traversable and within the board
                if (y == gridHeight || !checkTileIsTraversable(x, y, gridWidth)){
                    y--;
                }

                checkTile(x, y,gridWidth);

                break;
            case "UP":
                // Up key was pressed. So move the player right by one cell.
                y--;

                //Checks if the tile is traversable and within the board
                if (y < 0 || !checkTileIsTraversable(x, y, gridWidth)) {
                    y++;
                }
                // check if the tile is dirt
                checkTile(x, y,gridWidth);
                break;
            default:
                // Do nothing for all other keys.
                break;
        }
    }

    /**
     * Checks if the new tile is a dirt tile, if so changes to path.
     * @param x the prospective x coordinate.
     * @param y the prospective y coordinate.
     * @param width the grids width.
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
        }
    }

    // TODO FIX BUG WITH LEVEL LOAD
    private void loadNextLevel() {
        try {
            // Loads the next level
            currentLevel.loadLevel("src/Level2.txt");
            // Resets the characters position for next level
            x = 0;
            y = 0;

            System.out.println("Loading next Level");
        } catch (FileNotFoundException e) {
            System.out.println("No level found");
        }
    }

    /**
     *
     * @param x the new player x.
     * @param y the new player y.
     * @param width the grid width.
     * @return
     */
    private boolean checkTileIsTraversable(int x, int y, int width){
        int tilePos = y * width + x;
        return tiles[tilePos].isTraversable() && (tiles[tilePos] != null);
    }

    /**
     *
     * @param tilePos the tiles position.
     * @return whether the tile is dirt.
     */
    private boolean checkDirtTile(int tilePos) {
        return tiles[tilePos].getTileToken().equals("D") && (tiles[tilePos] != null);
    }

    // TODO, implement that the level changes if exit is stood on (and min diamonds unlocked)
    private boolean checkExitTile(int tilePos) {
        return tiles[tilePos].getTileToken().equals("E") && (tiles[tilePos] != null);
    }

    // TODO implement that diamonds can be collected and then turn to paths
    private boolean checkDiamondTile(int tilePos) {
        return tiles[tilePos].getTileToken().equals("*") && (tiles[tilePos] != null);
    }

    private void transformToPath(int tilePos) {
        // check if the tile is destructible.
        if(tiles[tilePos].isDestructible()) {
            // turn the tile to path.
            tiles[tilePos] = new Path(x,y);
        }
    }


    /**
     * Getter for the characters X coordinate.
     * @return X the characters X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for the characters X coordinate.
     * @param x the characters new X coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for the characters Y coordinate.
     * @return Y the characters Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the characters Y coordinate.
     * @param y the characters new Y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }
}
