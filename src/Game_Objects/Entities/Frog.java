package Game_Objects.Entities;

/**
  * Frog class represent frog entity
  * @author elbucheung
*/
public class Frog extends Entity {
  private int playerPositionX;
  private int playerPositionY;
  private boolean playerReachable;


 public Frog(int x, int y) {
  super(x, y, false, false, 10, "F");

 }

  public Frog() {
    super(false, false, 10, "F");

  }



  // Track the player's position
  private void getPlayerPosition(Character character) {
    /*
    playerPositionX = character.getX();
    playerPositionY = character.getY();
     */
  }

  @Override
  protected void removeTarget() {

  }

  @Override
  protected void isColliding() {

  }

}
