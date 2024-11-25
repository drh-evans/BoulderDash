import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Level class to load and initialise a level file.
 * @author Nathan Welby, Rhys Evans
 */
public class Level {
    // Tile images
    private static final Image DIRT = new Image("Images/dirt.png");
    private static final Image PATH = new Image("Images/path.png");
    private static final Image WALL = new Image("Images/wall.png");
    private static final Image EXIT = new Image("Images/exit.png");
    private static final Image MAGIC_WALL = new Image("Images/wall.png");
    private static final Image TITANIUM_WALL = new Image("Images/titaniumwall.png");

    // Size of each cell on a grid.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 12;
    private static final int GRID_HEIGHT = 8;

    // TODO: Bug to be fixed (to do with the static)
    private static int width = 12;
    private static int height = 4;

    /*
    private static String[] tiles ={"P","P","W","D","P","P","D","P","D","D","D","P",
            "D","D","D","D","D","D","D","D","D","D","D","D",
            "P","P","P","P","W","P","P","P","P","P","P","P",
            "TW","TW","TW","TW","P","P","D","P","D","D","D","E"};
     */
    private static String[] tiles;

    /**
     *
     * @param levelPath levelPath, a file path to a level.txt file.
     * @throws FileNotFoundException
     */
    public Level (String levelPath) throws FileNotFoundException {
        loadLevel(levelPath);
    }

    /**
     * Loads a levels file data.
     * @param path path, the file path of the level file.
     * @throws FileNotFoundException
     */
    public static void loadLevel (String path) throws FileNotFoundException {
        try {
            // Find file and create scanner with the space delimiter.
            File level = new File(path);
            Scanner levelScanner = new Scanner(level);
            levelScanner.useDelimiter(" |\\n");

            height = levelScanner.nextInt();
            width = levelScanner.nextInt();

            System.out.println("Loaded height and width: ");
            System.out.println(height);
            System.out.println(width);

            int amountOfTiles = height * width;
            tiles = new String[amountOfTiles];

            System.out.println("Loaded tiles.");
            // Set the tiles into position in the array.
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] = levelScanner.next();
                System.out.print(tiles[i] + " ");
            }
            levelScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Method to initialise the visualisation of the level.
     * @param gc the graphics context of the canvas
     */
    public static void initialiseLevel(GraphicsContext gc) {
        // The row / columns.
        int row = 0;
        int col = 0;
        // The positions (X,Y) of the tile.
        int x = 0;
        int y = 0;

        // Check that the board hasn't been filled.
        while (col < width && row < height) {
            for (String tile : tiles) {
                // check tiles array for the type of tile.
                switch (tile) {
                    case "D":
                        gc.drawImage(DIRT, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    case "P":
                        gc.drawImage(PATH, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    case "E":
                        gc.drawImage(EXIT, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    case "W":
                        gc.drawImage(WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    case "MW":
                        gc.drawImage(MAGIC_WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    case "TW":
                        gc.drawImage(TITANIUM_WALL, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        break;
                    default:
                        break;
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

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
