package Entity;

public class Boulder extend Entity {
  protected boolean isPushable;

  public Boulder() {
    isPassive = true;
    canFall = false;
    isPushable = true;
    actorType = "Boulder";
  }

  protected void isColliding (MagicWall magicWall) {
    
    }

  protected void isColliding (Character character) {
  
  }
  protected void removeTarget (Character character) {
    character = null;
  }


}
