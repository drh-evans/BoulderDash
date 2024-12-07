package Game_Logic;

import Game_Objects.Tiles.Colours;
import Game_Objects.Tiles.Key;

import java.util.ArrayList;

/**
 * Class to build and store the players relevant data.
 * @author rhysevans
 */
public class Player {
    private static final int MAX_LEVEL = 10;
    private final String name;
    private Character playerCharacter;
    private int levelScore;
    private int finalLevelScore;
    private int levelUnlocked = 1;
    private ArrayList<Key> inventory = new ArrayList<>();
    /**
     *
     * @param name
     */
    public Player(String name, int levelUnlocked) {
        this.name = name;
        this.levelUnlocked = levelUnlocked;
    }

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

    public void setFinalLevelScore(Level currentLevel) {
        finalLevelScore = (int) ((levelScore * currentLevel.getTimeToComplete()) * 0.2);
    }

    public int getFinalLevelScore() {
        return finalLevelScore;
    }

    /**
     * Set the players highscore.
     * @param levelScore the new highscore achieved.
     */
    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
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
