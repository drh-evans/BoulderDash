package Game_Objects.Entities;

import Game_Objects.GameObject;

import java.util.ArrayList;
import java.util.Random;

public class Amoeba extends Entity {

    // Basic attributes for amoebas
    private int growthRate;
    private int maxSize;
    private int currentSize;
    private boolean isMaximum;
    private ArrayList<GameObject> neighbours = new ArrayList<>();


    public Amoeba(int x, int y, int growthRate, int maxSize) {
        super(x, y, true, false, 10, "A");
        this.growthRate = growthRate;
        this.maxSize = maxSize;
        this.currentSize = 1;
    }

    public Amoeba() {
        super(true, false, 10, "A");
        currentSize = 1;
    }
    
    private boolean isMaximum() {
        if (currentSize == maxSize) {
            return isMaximum = true;
        }
        return isMaximum;
    }

    public void spread(GameObject[] tiles, int width) {
        getNeighbours(tiles, width);

        if (!neighbours.isEmpty()) {
            if (!isMaximum()) {
                System.out.println(maxSize);
                System.out.println(currentSize);
                int currentTilePosition = (y * width) + x;
                int random = new Random().nextInt(neighbours.size() + 1);
                GameObject tile;
                if (random >= neighbours.size()) {
                    tile = neighbours.get(0);
                } else {
                    tile = neighbours.get(random);
                }
                int newTileX = tile.getX();
                int newTileY = tile.getY();
                int nextPosition = (newTileY * width) + newTileX;
                tiles[nextPosition] = new Amoeba(newTileX, newTileY, growthRate, maxSize);
                currentSize++;
            }
        }
    }
    public void getNeighbours(GameObject[] tiles, int width) {
        // Calculate the neighbouring tiles in each direction.
        int currentTilePosition = (y * width) + x;
        int rightNeighbourPosition = ((y * width) + (x+1));
        int leftNeighbourPosition = ((y * width) + (x - 1));

        int upperNeighbourPosition = (((y-1) * width) + x);
        int lowerNeighbourPosition = (((y+1) * width) + x);

        // Manage if the amoebas neighbours have been  discovered.
        boolean rightDiscovered = false;
        boolean leftDiscovered = false;
        boolean upperDiscovered = false;
        boolean lowerDiscovered = false;

        //  Add any valid paths to a neighbours array.
            // check the tiles in 4 cardinal directions.
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
     * Validate if the neighbour is dirt or a path.
     * @param tiles the array of game tiles.
     * @param position the neighbouring position.
     * @return true or false, depending on if the tile is a dirt or path or not.
     */
    public boolean validateNeighbour(GameObject[] tiles, int position) {
        if (tiles[position].getObjectToken().equals("P")) {
            return true;
        } else if (tiles[position].getObjectToken().equals("D")) {
            return true;
        }
        return false;
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

    /**
     *
     */
    @Override
    public void dropDiamond() {

    }
}
