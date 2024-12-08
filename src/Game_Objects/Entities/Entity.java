package Game_Objects.Entities;
import Game_Objects.GameObject;

/**
 * Class to represent all moving entities.
 * @author Elbu Cheung
 * @version 1.0
 */
public abstract class Entity extends GameObject {

  protected boolean isPassive;
  protected boolean canFall;
  protected int gameTick;

  /**
   * Constructs the entity.
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param isPassive if entity is passive or not.
   * @param canFall if entity can fall.
   * @param gameTick the game tick of the entity.
   * @param objectToken the specific token of the entity.
   */
  public Entity(int x, int y, boolean isPassive, boolean canFall, int gameTick, String objectToken) {
    super(x, y, objectToken);
    this.isPassive = isPassive;
    this.canFall = canFall;
    this.gameTick = gameTick;
  }

  /**
   * Position-less constructor.
   * @param isPassive if the entity is passive or not.
   * @param canFall if the entity can fall.
   * @param gameTick the game tick of the entity.
   * @param objectToken the specific token of the entity.
   */
  public Entity(boolean isPassive, boolean canFall, int gameTick, String objectToken) {
    super(objectToken);
    this.isPassive = isPassive;
    this.canFall = canFall;
    this.gameTick = gameTick;
  }
}