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

    /**
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        playerCharacter = new Character();
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
     * Increments the players level by 1
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
}
