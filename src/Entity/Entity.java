package Entity;

public abstract class Entity {
  protected boolean isPassive;
  protected boolean canFall;
  protected Tile position;
  protected String actorType;
  protected int gameTick;

  public abstract void removeTarget();
  public abstract void isColliding(); 
}
