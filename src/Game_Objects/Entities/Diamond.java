package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Player;

/**
 * Class to represent diamond entities.
 * @author Elbu Cheung, Rhys Evans.
 */
public class Diamond extends Entity {
    private boolean pickedUp;
    private boolean isFalling = false;

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
    /**
     * Method to set a characters score.
     * @param character the player profile.
     */
    public void collectDiamond(Character character) {
        Player currentPlayer = character.getCurrentPlayer();
        int oldScore = currentPlayer.getLevelScore();
        int newScore = oldScore + 10;
        character.setDiamondsCollected();
        currentPlayer.setLevelScore(newScore);
        pickedUp = true;
    }
}
