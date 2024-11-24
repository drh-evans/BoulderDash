import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {

    private int[] highScores;
    private int timeToComplete;
    private int diamondsToWin;
    private int amoebaSpreadRate;
    private int amoebaMaxSize;

    /**
     * Constructor for the level class, utilises file reading to initialise basic attributes
     * @param file {@code File} level file containing information about the board and level
     * @throws FileNotFoundException
     */
    public Level(File file) throws FileNotFoundException {
        FileInputStream streamReader = new FileInputStream(file);

        // Create a scanner to read the level attributes from its file
        Scanner fileScanner = new Scanner(streamReader);
        fileScanner.useDelimiter("--LEVELDATA");

        // Jump to the level data section of the file
        fileScanner.next();
        fileScanner.nextLine();

        // Create a new scanner to parse the highscores array
        Scanner scoresScanner = new Scanner(fileScanner.nextLine());
        scoresScanner.useDelimiter(",");
        int[] highScores = new int[10];
        int currentIndex = 0;

        // Add each score to the array
        while (scoresScanner.hasNext()) {
            highScores[currentIndex] = scoresScanner.nextInt();
            currentIndex++;
        }

        // Initialise the level attributes from their respective lines in the file
        int timeToComplete = Integer.parseInt(fileScanner.nextLine());
        int diamondsToWin = Integer.parseInt(fileScanner.nextLine());
        int amoebaSpreadRate = Integer.parseInt(fileScanner.nextLine());
        int amoebaMaxSize = Integer.parseInt(fileScanner.nextLine());
        fileScanner.close();

        this.highScores = highScores;
        this.timeToComplete = timeToComplete;
        this.diamondsToWin = diamondsToWin;
        this.amoebaSpreadRate = amoebaSpreadRate;
        this.amoebaMaxSize = amoebaMaxSize;

        Scanner dimensionReader = new Scanner(streamReader);
        int width = dimensionReader.nextInt();
        int height = dimensionReader.nextInt();

        Board board = new Board(height, width, file);

    }

    // TODO: Load levels
    public void initialiseLevel() {
        
    }

    public int[] getHighScores() {
        return highScores;
    }

    public void setHighScores(int[] highScores) {
        this.highScores = highScores;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getDiamondsToWin() {
        return diamondsToWin;
    }

    public void setDiamondsToWin(int diamondsToWin) {
        this.diamondsToWin = diamondsToWin;
    }

    public int getAmoebaSpreadRate() {
        return amoebaSpreadRate;
    }

    public void setAmoebaSpreadRate(int amoebaSpreadRate) {
        this.amoebaSpreadRate = amoebaSpreadRate;
    }

    public int getAmoebaMaxSize() {
        return amoebaMaxSize;
    }

    public void setAmoebaMaxSize(int amoebaMaxSize) {
        this.amoebaMaxSize = amoebaMaxSize;
    }
}
