package Game_Objects.Entities;
import Game_Objects.GameObject;
import Game_Objects.Tiles.MagicWall;
import Game_Objects.Tiles.Path;

/**
 * Class to represent Boulder entities.
 * @author Elbu Cheung, Rhys Evans
 */
public class Boulder extends Entity {
  private boolean isPushable;
  private static String pushDirection;

  /**
   * Constructor for instanstienising a boulder.
   */
  public Boulder() {
    super(true, true, 10, "B");
    isPushable = true;
  }

  /**
   * Constructor for making a new boulder i.e moving it.
   * @param x the new x.
   * @param y the new y.
   */
  public Boulder(int x, int y) {
    super(x, y, true, true, 10, "B");
    this.isPushable = false;
    this.isDestructible = true;
  }

  /**
   * Method to push a boulder if it is pushable.
   */
  public void push(GameObject[] gameObjects, int tilePos, int direction) {
    int newPos;
    if (direction == 1) {
      newPos = tilePos + 1;
      gameObjects[newPos] = this;
    } if (direction == 2) {
      newPos = tilePos - 1;
      gameObjects[newPos] = this;
    }
  }


  protected void isColliding (MagicWall magicWall) {
  }


  protected void isColliding (Character character) {
  
  }
  public void removeTarget (Character character) {
    character = null;
  }


  /**
   *
   */
  @Override
  protected void removeTarget() {

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

  public void setPushDirection(String direction) {
    switch (direction) {
      case "D", "RIGHT" -> pushDirection = "RIGHT";
      case "A", "LEFT" -> pushDirection = "LEFT";
      default -> pushDirection = "";
    }
  }

  public String getPushDirection() {
    return pushDirection;
  }
}
