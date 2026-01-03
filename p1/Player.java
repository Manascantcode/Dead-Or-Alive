package p1;

public class Player {
    public int x;
    public int y;
    public int lifeScore;
    public int deathScore;

    public Player() {
        this.x = 0;  // Start at top-left corner
        this.y = 0;
        this.lifeScore = 0;
        this.deathScore = 0;
    }

    // Move player to new position
    public void moveTo(int newX, int newY, int unused) {
        this.x = newX;
        this.y = newY;
    }

    // Returns formatted string for display on GUI
    public String statusString() {
        return String.format(
            "<html><b>Player Stats:</b><br>X: %d<br>Y: %d<br>Life Score: %d<br>Death Score: %d</html>",
            x, y, lifeScore, deathScore
        );
    }
}
