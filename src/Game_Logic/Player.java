package Game_Logic;

import Game_Objects.Tiles.Colours;
import Game_Objects.Tiles.Key;

import java.util.ArrayList;

/**
 * Class to build and store the players relevant data.
 * Controls the players inventory.
 * @author Rhys Evans, Adam Bartlett
 */
public class Player {
    private static final int MAX_LEVEL = 8;
    private final String name;
    private Character playerCharacter;
    private int levelScore;
    private int finalLevelScore;
    private int levelUnlocked = 1;
    private ArrayList<Key> inventory = new ArrayList<>();

    /**
     * Creates a new player object with a unique name and level unlocked.
     * @param name          Players name.
     * @param levelUnlocked The highest level this player has unlocked.
     */
    public Player(String name, int levelUnlocked) {
        this.name = name;
        this.levelUnlocked = levelUnlocked;
    }

    /**
     * Creates a new player object with a special name, level unlocked
     * and desired level
     * @param name          Players name.
     * @param levelUnlocked The highest level this player has unlocked.
     * @param desiredLevel  The level in which this player will be used.
     */
    public Player(String name, int levelUnlocked, Level desiredLevel) {
        this.name = name;
        this.levelUnlocked = levelUnlocked;
        this.playerCharacter = new Character(desiredLevel, this);
    }


    /**
     * Increments the players level by 1.
     */
    public void progressLevel() {
        if (levelUnlocked < MAX_LEVEL) {
            // increments by 1 if less than max level.
            levelUnlocked++;
        } else {
            // otherwise sets to the max level.
            levelUnlocked = MAX_LEVEL;
        }
    }

    /**
     * Adds a key to a players inventory.
     * @param key the key obtained by the player.
     */
    public void addToInventory(Key key) {
        inventory.add(key);
        for (Key item : inventory) {
            System.out.println("KEY COLLECTED: " + item.getObjectToken());
        }
    }

    /**
     * Checks the inventory for matching key.
     * @param colour The colour of the door.
     * @return hasKey, whether they have a key or not.
     */
    public boolean checkInventory(Colours colour) {
        boolean hasKey = false;
        for (Key key : inventory) {
            if (key.getColour() == colour) {
                hasKey = true;
            }
        }
        return hasKey;
    }

    /**
     * Remove a key.
     * @param colour The doors colour.
     */
    public void removeKey(Colours colour) {
        // Search for the key.
        inventory.removeIf(key -> key.getColour() == colour);
    }

    /**
     * Getter for the Players name.
     * @return the username of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the players highest score.
     * @return the players highest score.
     */
    public int getLevelScore() {
        return levelScore;
    }

    /**
     * Set the players highscore.
     * @param levelScore the new highscore achieved.
     */
    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    /**
     * Get the players final level score.
     * @return the players final level score.
     */

    public int getFinalLevelScore() {
        return finalLevelScore;
    }

    /**
     * Set the players final level score.
     * @param currentLevel the level the player is currently on.
     */
    public void setFinalLevelScore(Level currentLevel) {
        finalLevelScore = ((levelScore * currentLevel.getTimeToComplete()) / 5);
    }

    /**
     * Get the players highest unlocked level.
     * @return the highest level unlocked by a player.
     */
    public int getLevelUnlocked() {
        return levelUnlocked;
    }

    /**
     * Set the new highest level unlocked.
     * @param levelUnlocked the new highest level playable.
     */
    public void setLevelUnlocked(int levelUnlocked) {
        this.levelUnlocked = levelUnlocked;
    }

    /**
     * Gets the players representative.
     * @return the players representative.
     */
    public Character getPlayerCharacter() {
        return playerCharacter;
    }
}
