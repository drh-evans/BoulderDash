package Game_Objects.Entities;

import Game_Objects.GameObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the Amoeba entity.
 * This class also controls the spreading of an Amoeba,
 * and the behaviour of a group of Amoebae.
 * @author Rhys Evans
 * @version 1.0
 */
public class Amoeba extends Entity {

    // The current size of a group of Amoebae.
    private static int currentSize = 0;
    // The maxmimum size and growth rate of Amoeba.
    private int growthRate;
    private int maxSize;
    private boolean isMaximum;
    // The neighbouring tiles to an Amoeba.
    private ArrayList<GameObject> neighbours = new ArrayList<>();
    // The active Amoebae.
    private ArrayList<GameObject> activeAmoeba = new ArrayList<>();


    /**
     * Create a new Amoeba at the specified (x, y) coordinates.
     *
     * @param x          The x position.
     * @param y          The y position.
     * @param growthRate The growth rate of the Amoeba.
     * @param maxSize    The maximum size of Amoebae.
     */
    public Amoeba(int x, int y, int growthRate, int maxSize) {
        super(x, y, true, false, 10, "A");
        this.growthRate = growthRate;
        this.maxSize = maxSize;
    }

    /**
     * Create a positionless Amoeba.
     */
    public Amoeba() {
        super(true, false, 10, "A");
    }

    /**
     * Check if Amoebae have reached the maximum size.
     *
     * @return isMaximum - true or false
     */
    private boolean isMaximum() {
        if (currentSize == maxSize) {
            return isMaximum = true;
        }
        return isMaximum;
    }

    /**
     * Spread the amoeba.
     *
     * @param tiles The current game tiles.
     * @param width The current game width.
     */
    public void spread(GameObject[] tiles, int width) {
        getNeighbours(tiles, width);
        System.out.println(currentSize);

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
                    if (!activeAmoeba.contains(tile)) {
                        int newTileX = tile.getX();
                        int newTileY = tile.getY();
                        int nextPosition = (newTileY * width) + newTileX;
                        // Generate a new Amoeba at the position.
                        tiles[nextPosition] = new Amoeba(newTileX, newTileY, growthRate, maxSize);
                        neighbours.add(tiles[nextPosition]);
                        activeAmoeba.add(tile);
                        currentSize++;
                    }
                }
            }
        }

    }

    /**
     * Get the neighbours of an active amoeba.
     *
     * @param tiles The current tiles.
     * @param width The game width.
     */
    public void getNeighbours(GameObject[] tiles, int width) {

        // Calculate the neighbouring tiles in each direction.
        int currentTilePosition = (y * width) + x;
        int rightNeighbourPosition = currentTilePosition + 1;
        int leftNeighbourPosition = currentTilePosition - 1;

        int upperNeighbourPosition = (((y - 1) * width) + x);
        int lowerNeighbourPosition = (((y + 1) * width) + x);


        //  Add any valid paths to a neighbours array.
        // Check the tiles in 4 cardinal directions.
        if (validateNeighbour(tiles, rightNeighbourPosition)) {
            neighbours.add(tiles[rightNeighbourPosition]);
        } else if (validateNeighbour(tiles, leftNeighbourPosition)) {
            neighbours.add(tiles[leftNeighbourPosition]);
        } else if (validateNeighbour(tiles, upperNeighbourPosition)) {
            neighbours.add(tiles[upperNeighbourPosition]);
        } else if (validateNeighbour(tiles, lowerNeighbourPosition)) {
            neighbours.add(tiles[lowerNeighbourPosition]);
        }
    }

    /**
     * Validate the neighbours tile type.
     * @param tiles    The array of game tiles.
     * @param position The neighbouring position.
     * @return If the neighbour is spreadable - true or false.
     */
    public boolean validateNeighbour(GameObject[] tiles, int position) {
        if (tiles[position].getObjectToken().equals("P")) {
            return true;
        } else return tiles[position].getObjectToken().equals("D");
    }

    /**
     * Turns a group of Amoebae to Diamonds.
     *
     * @param tiles     The current tiles.
     * @param gridWidth The grid width.
     */
    private void turnToBoulders(GameObject[] tiles, int gridWidth) {
        // Check all amoebae.
        for (GameObject amoebae : activeAmoeba) {
            x = amoebae.getX();
            y = amoebae.getY();
            gridWidth = gridWidth;
            int position = (y * gridWidth) + x;
            // Set amoeba to be a Boulder.
            tiles[position] = new Boulder(x, y);
            // Kill the Amoeba.
            activeAmoeba.remove(amoebae);
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
