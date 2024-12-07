package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Player;

/**
 * This class represents the Diamond collectable.
 * Also handles the collection of Diamonds.
 * @author Rhys Evans, Elbu Cheung.
 */
public class Diamond extends Entity {


    private boolean pickedUp;
    private boolean isFalling = false;

    /**
     * Create a Diamond at the coordinates (x, y).
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Diamond(int x, int y) {
        super(x, y, true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    public Diamond() {
        super(true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
        this.isDestructible = true;
    }

    /**
     * Collects a diamond.
     * @param character The players' character.
     */
    public void collectDiamond(Character character) {
        // Get the current player profile.
        Player currentPlayer = character.getCurrentPlayer();

        // Get the previous score.
        int oldScore = currentPlayer.getLevelScore();
        int newScore = oldScore + 10;
        character.setDiamondsCollected();

        // Calculate the final score.
        currentPlayer.setLevelScore(newScore);

        pickedUp = true;
    }

    /**
     *
     */
    @Override
    protected void removeTarget() {
        if (pickedUp) {

        }
    }

    /**
     *
     */
    @Override
    protected void isColliding() {

    }

    /**
     *
     */
    @Override
    protected void dropDiamond() {

    }

    public void drop() {
    }
}
