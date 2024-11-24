package Entity;

public class Frog extends Enemy {
  private int[][] playerPosition;
  private boolean playerReachable;

  public Frog() {
    playerPosition = = new int[1][2];
  }
    

  // Track the player's position
  private void getPlayerPosition(Character character) {
    playerPosition[0][0] = character.getX();
    playerPosition[0][1] = character.getY();
  }

  
}
