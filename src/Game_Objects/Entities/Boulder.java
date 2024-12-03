package Game_Objects.Entities;
import Game_Objects.Tiles.MagicWall;

public class Boulder extends Entity {
  private boolean isPushable;


  public Boulder(int x, int y) {
    super(x, y, true, true, 10, "B");
    this.isPushable = false;
  }

  public Boulder() {
    super(true, true, 10, "B");
    isPushable = false;
  }

  protected void isColliding (MagicWall magicWall) {
  }


  protected void isColliding (Character character) {
  
  }
  protected void removeTarget (Character character) {
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
}
