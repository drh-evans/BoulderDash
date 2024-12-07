package Game_Objects.Entities;

import Game_Objects.GameObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the Amoeba entity.
 * This class also controls the spreading of an Amoeba,
 * and the behaviour of a group of Amoebae.
 * @author Rhys Evans
 */
public class Amoeba extends Entity {

    // The maxmimum size and growth rate of Amoeba.
    private int growthRate;
    private int maxSize;
    private boolean isMaximum;

    // The current size of a group of Amoebae.
    private static int currentSize = 1;

    // The neighbouring tiles to an Amoeba.
    private ArrayList<GameObject> neighbours = new ArrayList<>();
    // The active Amoebae.
    private ArrayList<Amoeba> activeAmoeba = new ArrayList<>();


    /**
     * Create a new Amoeba at the specified (x, y) coordinates.
     * @param x The x position.
     * @param y The y position.
     * @param growthRate The growth rate of the Amoeba.
     * @param maxSize The maximum size of Amoebae.
     */
    public Amoeba(int x, int y, int growthRate, int maxSize) {
        super(x, y, true, false, 10, "A");
        this.growthRate = growthRate;
        this.maxSize = maxSize;
    }

    public Amoeba() {
        super(true, false, 10, "A");
    }

    private boolean isMaximum() {
        if (currentSize == maxSize) {
            return isMaximum = true;
        }
        return isMaximum;
    }

    /**
     * Spreads the amoeba.
     * @param tiles The current game tiles.
     * @param width The current game width.
     */
    public void spread(GameObject[] tiles, int width) {
        getNeighbours(tiles, width);

        if (!neighbours.isEmpty()) {
            if (currentSize < maxSize) {
                int currentTilePosition = (y * width) + x;

                // Get a random number to select a random neighbour.
                int random = new Random().nextInt(neighbours.size());
                GameObject tile;

                // Select a random neighbour.
                if (random >= neighbours.size()) {
                    tile = neighbours.get(0);
                } else {
                    tile = neighbours.get(random);
                }
                if (tile.getObjectToken().equals("P") || tile.getObjectToken().equals("D")) {
                    int newTileX = tile.getX();
                    int newTileY = tile.getY();
                    int nextPosition = (newTileY * width) + newTileX;

                    // Generate a new Amoeba at the position.
                    tiles[nextPosition] = new Amoeba(newTileX, newTileY, growthRate, maxSize);
                    activeAmoeba.add((Amoeba) tiles[nextPosition]);
                    currentSize++;
                }
            }
        }
    }

    /**
     * Get the neighbours of an active amoeba.
     * @param tiles The current tiles.
     * @param width The game width.
     */
    public void getNeighbours(GameObject[] tiles, int width) {
        // Calculate the neighbouring tiles in each direction.
        int currentTilePosition = (y * width) + x;
        int rightNeighbourPosition = ((y * width) + (x + 1));
        int leftNeighbourPosition = ((y * width) + (x - 1));

        int upperNeighbourPosition = (((y-1) * width) + x);
        int lowerNeighbourPosition = (((y+1) * width) + x);

        // Manage if the amoebas neighbours have been  discovered.
        boolean rightDiscovered = false;
        boolean leftDiscovered = false;
        boolean upperDiscovered = false;
        boolean lowerDiscovered = false;

        //  Add any valid paths to a neighbours array.
        // Check the tiles in 4 cardinal directions.
        if (validateNeighbour(tiles, rightNeighbourPosition) && !rightDiscovered) {
            neighbours.add(tiles[rightNeighbourPosition]);
            rightDiscovered = true;
        } else if (validateNeighbour(tiles, leftNeighbourPosition) && !leftDiscovered) {
            neighbours.add(tiles[leftNeighbourPosition]);
            leftDiscovered = true;
        } else if (validateNeighbour(tiles, upperNeighbourPosition) && !upperDiscovered) {
            neighbours.add(tiles[upperNeighbourPosition]);
            upperDiscovered = true;
        } else if (validateNeighbour(tiles, lowerNeighbourPosition) && !lowerDiscovered) {
            neighbours.add(tiles[lowerNeighbourPosition]);
            lowerDiscovered = true;
        }
    }

    /**
     * Validate the neighbours type.
     * @param tiles The array of game tiles.
     * @param position The neighbouring position.
     * @return If the neighbour is spreadable.
     */
    public boolean validateNeighbour(GameObject[] tiles, int position) {
        if (tiles[position].getObjectToken().equals("P")) {
            return true;
        } else if (tiles[position].getObjectToken().equals("D")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    @Override
    public void removeTarget() {

    }

    /**
     *
     */
    @Override
    public void isColliding() {

    }
}
