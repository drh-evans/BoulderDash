package Entity;

public abstract class Entity {
  protected boolean isPassive;
  protected boolean canFall;
  protected Tile position;
  protected String actorType;
  protected int gameTick;

  protected abstract void removeTarget();
  protected abstract void isColliding(); 
  protected abstract void dropDiamond();
}
