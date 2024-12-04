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
    private final Character playerCharacter;
    private int highScore;
    private int levelUnlocked = 1;
    private ArrayList<Key> inventory = new ArrayList<>();
    /**
     *
     * @param name
     */
    public Player(String name, Level desiredLevel) {
        this.name = name;
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
            System.out.println(item.getObjectToken());
        }
    }

    /**
     * Checks if a player has a key for a door.
     * @param colour the colour of the door.
     * @return true or false, whether the player has the key.
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
     * Remove a key once used.
     * @param colour the keys colour.
     */
    public void removeKey(Colours colour) {
        // if the key is the same colour as the door used, remove it.
        inventory.removeIf(key -> key.getColour() == colour);
    }

    /**
     * Checks the inventory for a specific key.
     * @param token the keys object token.
     * @return boolean false (doesn't have), true (has the key).
     */
    public boolean checkInventory(String token) {
        for (Key key : inventory) {
            if (key.getObjectToken().equals(token)) {
                return true;
            }
        }
        return false;
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
    public int getHighScore() {
        return highScore;
    }

    /**
     * Set the players highscore.
     * @param highScore the new highscore achieved.
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
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
