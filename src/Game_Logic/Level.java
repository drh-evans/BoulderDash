package Game_Logic;

import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Dirt;
import Game_Objects.Tiles.Exit;
import Game_Objects.Tiles.MagicWall;
import Game_Objects.Tiles.Path;
import Game_Objects.Tiles.Tile;
import Game_Objects.Tiles.TitaniumWall;
import Game_Objects.Tiles.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Level class to load and initialise a level file.
 *
 * @author Nathan Welby, Rhys Evans
 */
public class Level {
    // Tile images
    private static final Image DIRT = new Image("Images/dirt.png");
    private static final Image PATH = new Image("Images/path.png");
    private static final Image WALL = new Image("Images/wall.png");
    private static final Image EXIT = new Image("Images/exit.png");
    private static final Image MAGIC_WALL = new Image("Images/magicwall.png");
    private static final Image TITANIUM_WALL = new Image("Images/titaniumwall.png");
    private static final Image DIAMOND = new Image("Images/diamond.png");
    private static final Image BOULDER = new Image("Images/boulder.png");
    private static final Image FIREFLY = new Image("Images/firefly.png");
    private static final Image BUTTERFLY = new Image("Images/butterfly.png");
    private static final Image GREEN_KEY = new Image("Images/greenkey.png");
    private static final Image BLUE_KEY = new Image("Images/bluekey.png");
    private static final Image YELLOW_KEY = new Image("Images/yellowkey.png");
    private static final Image RED_KEY = new Image("Images/redkey.png");


    // Size of each cell on a grid.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // Txt file where level information is stored
    private final File levelFile;

    // Top ten highest scores of this level
    private final int[] highscores = new int[10];
    private final int diamondsToWin;
    private final int amoebaSpreadRate;
    private final int amoebaMaxSize;
    // Auxiliary level attributes (not currently in use)
    private int timeToComplete;
    // Width and height of level in tiles
    private int width;
    private int height;

    // Array to store tile placeholders
    private GameObject[] gameBoard;

    /**
     * Initialises auxiliary attributes of the level
     *
     * @param levelPath levelPath, a file path to a level.txt file.
     * @throws FileNotFoundException
     */
    public Level(String levelPath) throws FileNotFoundException {
        // initialise the levelFile attribute for future reference
        levelFile = new File(levelPath);

        // Create a scanner to read the level attributes from its file
        Scanner fileScanner = new Scanner(levelFile);
        fileScanner.useDelimiter("--LEVELDATA");

        // Jump to the level data section of the file
        fileScanner.next();
        fileScanner.nextLine();

        // Create a new scanner to parse the highscores array
        Scanner scoresScanner = new Scanner(fileScanner.nextLine());
        scoresScanner.useDelimiter(",");
        int currentIndex = 0;

        // Add each score to the array
        while (scoresScanner.hasNext()) {
            getHighscores()[currentIndex] = scoresScanner.nextInt();
            currentIndex++;
        }

        // Initialise the level attributes from their respective lines in the file
        timeToComplete = Integer.parseInt(fileScanner.nextLine());
        diamondsToWin = Integer.parseInt(fileScanner.nextLine());
        amoebaSpreadRate = Integer.parseInt(fileScanner.nextLine());
        amoebaMaxSize = Integer.parseInt(fileScanner.nextLine());
        fileScanner.close();

        // call the loadLevel() method to handle the tile array
        loadLevel(levelPath);
    }

    /**
     * Loads a level's tile data.
     *
     * @param path path, the file path of the level file.
     * @throws FileNotFoundException
     */
    public void loadLevel(String path) throws FileNotFoundException {
        // Find file and create scanner with the space delimiter.
        File level = new File(path);
        Scanner levelScanner = new Scanner(level);

        // Initialise level height and width
        height = Integer.parseInt(levelScanner.next());
        width = Integer.parseInt(levelScanner.next());

        // Set the max size of the tile array equal to the number of tile tokens in the file
        gameBoard = new GameObject[width * height];
        GameObject currentObject;
        HashMap<String, GameObject> objectTokenMap = GameObject.getObjectTokenMap();

        /* Set the tiles into position in the array.
         * Reads the array one row at a time.
         * Keeps track of position using a nested for loop .
         */
        for (int y = 0; y < height; y++) {
            if (levelScanner.hasNextLine()) {
                levelScanner.nextLine();
                for (int x = 0; x < width; x++) {
                    if (levelScanner.hasNext()) {
                        currentObject = objectTokenMap.get(levelScanner.next());
                        // Check for tiles that match the input and then create the tiles/entities.
                        switch (currentObject.getObjectToken()) {
                            case "D" -> gameBoard[(y * width) + x] = new Dirt(x, y);
                            case "P" -> gameBoard[(y * width) + x] = new Path(x, y);
                            case "W" -> gameBoard[(y * width) + x] = new Wall(x, y);
                            case "M" -> gameBoard[(y * width) + x] = new MagicWall(x, y);
                            case "T" -> gameBoard[(y * width) + x] = new TitaniumWall(x, y);
                            case "E" -> gameBoard[(y * width) + x] = new Exit(x, y);
                            case "*" -> gameBoard[(y * width) + x] = new Diamond(x, y);
                            case "B" -> gameBoard[(y * width) + x] = new Boulder(x, y);
                            default -> System.out.println("No tile found");
                        }
                    }
                }
            }
        }

        // Print the array contents and size for testing
        for (GameObject object : gameBoard) {
            System.out.print(object.getObjectToken() + "(" + object.getX() + "," + object.getY() + ")" + " ");
        }
        System.out.println("Tile array size: " + gameBoard.length);
        levelScanner.close();
    }

    /**
     * Method to initialise the visualisation of the level.
     *
     * @param gc the graphics context of the canvas
     */
    public void initialiseLevel(GraphicsContext gc) {
        // The row / columns.
        int row = 0;
        int col = 0;
        // The positions (X,Y) of the tile.
        int x = 0;
        int y = 0;

        // Check that the board hasn't been filled.
        while (col < width && row < height) {
            for (GameObject object : gameBoard) {
                // check tiles array for the type of tile.
                switch (object.getObjectToken()) {
                    case "D" -> gc.drawImage(DIRT, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "P" -> gc.drawImage(PATH, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "E" -> gc.drawImage(EXIT, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "W" -> gc.drawImage(WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "M" -> gc.drawImage(MAGIC_WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "T" -> gc.drawImage(TITANIUM_WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "*" -> gc.drawImage(DIAMOND, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "B" -> gc.drawImage(BOULDER, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "FR", "FL" -> gc.drawImage(FIREFLY, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    case "BR", "BL" -> gc.drawImage(BUTTERFLY, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    default -> System.out.println("No valid tile found!");
                }

                x++;
                col++;
                // if a column is full, increment to next row and restart.
                if (col == width) {
                    col = 0;
                    x = 0;
                    row++;
                    y++;
                }
            }
        }
    }

    /**
     * Destroys the tile at the players position if it is destructible
     *
     * @param player
     */
    public void destroyTile(Player player) {
        // Retrieve the X and Y coordinates of the character
        int tilePos = player.getPlayerCharacter().getY() * width + player.getPlayerCharacter().getX();

        // Only proceed if the object is a Tile subclass
        if (gameBoard[tilePos].getClass().isAssignableFrom(Tile.class)) {
            // Creates a tile object so isDestructible() can be used
            Tile tile = (Tile) gameBoard[tilePos];

            // Checks if the occupied tile is destructible
            if (tile.isDestructible()) {
                gameBoard[tilePos] = new Path();
            }
        }
    }

    /**
     * Track and rank the top 10 score of level
     *
     * @param newHighScore
     */
    public void newHighScore(int newHighScore) {
        for (int i = 0; i < highscores.length; i++) {
            if (newHighScore > highscores[i]) {
                for (int j = highscores.length - 1; j > i; j--) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = newHighScore;
                break;
            }
        }
    }


    /**
     * Gets the array of game objects
     *
     * @return {@code GameObject[]} the array of game objects
     */
    public GameObject[] getGameBoard() {
        return gameBoard;
    }

    /**
     * Returns the width of the level in tiles
     *
     * @return {@code int} the width of the level in tiles
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the level in tiles
     *
     * @return {@code int} the height of the level in tiles
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the amount of time given to complete the level
     *
     * @return {@code int} the amount of time to complete the level
     */
    public int getTimeToComplete() {
        return timeToComplete;
    }

    /**
     * Returns the amount of diamonds required to complete the level
     *
     * @return {@code int} the amount of diamonds required to beat the level
     */
    public int getDiamondsToWin() {
        return diamondsToWin;
    }

    /**
     * Returns the spread rate of amoeba in the level
     *
     * @return {@code int} the spread rate of amoeba in the level
     */
    public int getAmoebaSpreadRate() {
        return amoebaSpreadRate;
    }

    /**
     * Returns the maximum size of amoeba in the level
     *
     * @return {@code int} the maximum size of amoeba in the level
     */
    public int getAmoebaMaxSize() {
        return amoebaMaxSize;
    }

    /**
     * Gets the array of the top ten scores of the level
     *
     * @return {@code int[]} top ten scores of the level
     */
    public int[] getHighscores() {
        return highscores;
    }

    public void setTimeToComplete() {
        timeToComplete--;
    }
}
