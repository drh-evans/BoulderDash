/**
  * Frog class represent frog entity
  * @author elbucheung
*/
public class Frog extends Enemy {
  private int playerPositionX;
  private int playerPositionY;
  private boolean playerReachable;


 public Frog (boolean isPassive, boolean canFall, Tile position, String actorType, int gameTick) {
  super(false, false, position, "Frog", gameTick);
 }

  // Track the player's position
  private void getPlayerPosition(Character character) {
    playerPositionX = character.getX();
    playerPositionY = character.getY();
  }

  
}
