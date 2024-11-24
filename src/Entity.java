public abstract class Entity {
  protected boolean isPassive;
  protected boolean canFall;
  // protected Tile position;
  protected String actorType;
  protected int gameTick;

  /**
  public Entity(boolean isPassive, boolean canFall, Tile position, String actorType, int gameTick) {
    this.isPassive = isPassive;
    this.canFall = canFall;
    this.position = position;
    this.actorType = actorType;
    this.gameTick = gameTick;
  }
   */

  protected abstract void removeTarget();
  protected abstract void isColliding(); 
  protected abstract void dropDiamond();
}
