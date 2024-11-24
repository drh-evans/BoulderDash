public class Boulder extends Entity {
  protected boolean isPushable;

  public Boulder(boolean isPassive, boolean canFall, Tile position, String actorType, int gameTick, boolean isPushable) {
    super(true, canFall, position, "Boulder", gameTick);
    this.isPushable = isPushable;
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
