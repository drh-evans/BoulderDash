package Game_Objects.Entities;
import Game_Objects.GameObject;
import Game_Objects.Tiles.MagicWall;

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

  public void pushableCheck(GameObject[] gameObjects, int newPos) {
    // all the tiles you cant push against - need to add them all
    // magic wall to diamond functionality can be added here
    if (!gameObjects[newPos].getObjectToken().equals("P")) {
      this.isPushable = false;
    } else if (gameObjects[newPos].getObjectToken().equals("M")) {
      // in the case of magic wall,
      // convert to diamond
    } else this.isPushable = true;
  }

  /**
   * Method to push a boulder if it is pushable.
   *
   * @return
   */
  public boolean push(GameObject[] gameObjects, int tilePos, int direction) {
    // ive tried to implement this as straight-forward and simply as possible, please refactor as seen fit
    int newPos;
    if (direction == 1) {
      newPos = tilePos + 1;
      pushableCheck(gameObjects, newPos);
      if (this.isPushable) {
        gameObjects[newPos] = this;
        return true;
      }
      return false;
    } if (direction == 2) {
      newPos = tilePos - 1;
      if (this.isPushable) {
        gameObjects[newPos] = this;
        return true;
      }
      return false;
    } if (direction == 3) {
      return false;
    } if (direction == 4) {
      return false;
    }
    return false;
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


  public String getPushDirection() {
    return pushDirection;
  }
}
