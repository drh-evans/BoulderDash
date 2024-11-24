package Entity;

public class Boulder extend Entity {
  protected boolean isPushable;

  public Boulder() {
    isPassive = true;
    canFall = false;
    actorType = "Boulder";
  }

  private void isColliding (MagicWall magicWall) {
    
    }

  private void isColliding (Character character) {
  
  }
  public void removeTarget (Character character) {
    character = null;
  }


}
