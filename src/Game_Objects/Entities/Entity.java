package Game_Objects.Entities;
import Game_Objects.GameObject;

public abstract class Entity extends GameObject {

  protected boolean isPassive;
  protected boolean canFall;
  protected int gameTick;

  public Entity(int x, int y, boolean isPassive, boolean canFall, int gameTick, String objectToken) {
    super(x, y, objectToken);
    this.isPassive = isPassive;
    this.canFall = canFall;
    this.gameTick = gameTick;
  }

  public Entity(boolean isPassive, boolean canFall, int gameTick, String objectToken) {
    super(objectToken);
    this.isPassive = isPassive;
    this.canFall = canFall;
    this.gameTick = gameTick;
  }

  protected abstract void removeTarget();
  protected abstract void isColliding(); 
  protected abstract void dropDiamond();
}
