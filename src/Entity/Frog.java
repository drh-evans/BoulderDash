package Entity;

/**
  * Frog class represent frog entity
  * @author elbucheung
*/
public class Frog extends Enemy {
  private int playerPositionX;
  private int playerPositionY;
  private boolean playerReachable;

  // Track the player's position
  private void getPlayerPosition(Character character) {
    playerPositionX = character.getX();
    playerPositionY = character.getY();
  }

  
}
