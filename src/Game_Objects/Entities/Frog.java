package Game_Objects.Entities;

import Game_Logic.Level;
import Game_Objects.GameObject;

import java.util.Arrays;

/**
  * Frog class represent frog entity
  * @author Davide Belsole, Elbu Cheung
*/
public class Frog extends Entity {
  private int playerPositionX;
  private int playerPositionY;
  private boolean playerReachable;


 public Frog(int x, int y) {
  super(x, y, false, false, 10, "F");

 }

  public Frog() {
    super(false, false, 10, "F");

  }

  private boolean valid(int x, int y, Level level) {
     int width  = level.getWidth();
     int height = level.getHeight();

     if (x < 0 || x >= width || y < 0 || y >= height) {
         return false;
     }
     int frogPos = y * width + x;

     return level.getGameBoard()[frogPos].isTraversable();
  }

  public void chasePlayer(Level level) {
      int posX = this.getX();
      int posY = this.getY();

      if (posX < playerPositionX && valid(posX + 1, posY, level)) {
          this.setX(posX + 1); // Move right
      } else if (posX > playerPositionX && valid(posX - 1, posY, level)) {
          this.setX(posX - 1); // Move left
      } else if (posY < playerPositionY && valid(posX, posY + 1, level)) {
          this.setY(posY + 1); // Mov down
      } else if (posY > playerPositionY && valid(posX, posY - 1, level)) {
          this.setY(posY - 1); // Move up
      }
  }


  // Track the player's position
  private void getPlayerPosition(Character character) {
    /*
    playerPositionX = character.getX();
    playerPositionY = character.getY();
     */
  }

  @Override
  protected void removeTarget() {

  }

  @Override
  protected void isColliding() {

  }

}
