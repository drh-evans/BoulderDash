import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    private int width;
    private int height;
    private final Tile[][] tileBoard = new Tile[height][width];
    private final Entity[][] entityBoard = new Entity[height][width];

    /**
     * <b>Incomplete</b> constructor for Board, need to add tile type functionality
     * @param height {@code int}: height of the board
     * @param width {@code int}: width of the board
     * @param file {@code File}: file to read the board information from
     * @throws FileNotFoundException
     */
    public Board(int height, int width, File file) throws FileNotFoundException {
        this.height = height;
        this.width = width;

        // Create a new scanner to read through the level file
        Scanner fileScanner = new Scanner(new FileInputStream(file));
        String tileCode;

        // Loop through the tile token array in the file
        for (int y = 0; y < height; y++) {
            fileScanner.nextLine();
            for (int x = 0; x < width; x++) {
                // Get the current tile token and initialise a new tile object at that position
                tileCode = fileScanner.next();

                switch(tileCode) {
                    case "D":
                        tileBoard[y][x] = new Dirt();

                    case "P":
                        tileBoard[y][x] = new Path();
                }
            }
        }
    }
}
