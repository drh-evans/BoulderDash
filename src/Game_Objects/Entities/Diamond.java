package Game_Objects.Entities;

import Game_Logic.Character;
import Game_Logic.Player;

public class Diamond extends Entity {
    private boolean pickedUp;

    public Diamond(int x, int y) {
        super(x, y, true, true, 10, "*");
        this.pickedUp = false;
        this.isTraversable = true;
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

    /**
     * Method to set a characters score.
     * @param character the player profile.
     */
    public void collectDiamond(Character character) {
        int oldScore = character.getScore();
        int newScore = oldScore + 10;
        character.setDiamondsCollected();
        character.setScore(newScore);
        pickedUp = true;
    }
}
