public class MapConfig {
    public int n; // grid size
    public int[][] trapPositions; // array of {x, y} for traps
    public int[] exitPosition;    // {x, y} for exit
    public String[][] clues;      // clue for each room (n x n)

    public MapConfig(int n, int[][] traps, int[] exit, String[][] clues) {
        this.n = n;
        this.trapPositions = traps;
        this.exitPosition = exit;
        this.clues = clues;
    }
}
