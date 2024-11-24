package Entity;

public class Frog extends Enemy {
  private int[][] playerPosition = new int[1][2];
  private boolean playerReachable;

  // Track the player's position
  private void getPlayerPosition(Character character) {
    playerPosition[0][0] = character.getX();
    playerPosition[0][1] = character.getY();
}
