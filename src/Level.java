public class Level {
    private int[] highScores;
    private int timeToComplete;
    private int diamondsToWin;
    private  int amoebaSpreadRate;
    private int amoebaMaxSize;

    // TODO: Load levels
    public void initialiseLevel() {
    }

    public int[] getHighScores() {
        return highScores;
    }

    public void setHighScores(int[] highScores) {
        this.highScores = highScores;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getDiamondsToWin() {
        return diamondsToWin;
    }

    public void setDiamondsToWin(int diamondsToWin) {
        this.diamondsToWin = diamondsToWin;
    }

    public int getAmoebaSpreadRate() {
        return amoebaSpreadRate;
    }

    public void setAmoebaSpreadRate(int amoebaSpreadRate) {
        this.amoebaSpreadRate = amoebaSpreadRate;
    }

    public int getAmoebaMaxSize() {
        return amoebaMaxSize;
    }

    public void setAmoebaMaxSize(int amoebaMaxSize) {
        this.amoebaMaxSize = amoebaMaxSize;
    }
}
