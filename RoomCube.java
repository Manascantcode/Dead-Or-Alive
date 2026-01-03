public class RoomCube {
    public boolean isTrap;      // true if this room contains a trap
    public boolean isExit;      // true if this room is the exit
    public boolean visited;     // true if player has visited this room
    public boolean deadEnd;     // true if the player died here
    public int roomNumber;      // unique room number for display or logic
    public String clue;         // new: hint for the player

    // Constructor
    public RoomCube(boolean isTrap, boolean isExit, int roomNumber) {
        this.isTrap = isTrap;
        this.isExit = isExit;
        this.roomNumber = roomNumber;
        this.visited = false;
        this.deadEnd = false;
        this.clue = ""; // default empty
    }

    // Optional helper: reset room state (if you ever restart maze)
    public void reset() {
        this.visited = false;
        this.deadEnd = false;
        this.clue = "";
    }

    // To easily print room info for debugging
    @Override
    public String toString() {
        return "RoomCube{" +
                "roomNumber=" + roomNumber +
                ", isTrap=" + isTrap +
                ", isExit=" + isExit +
                ", visited=" + visited +
                ", deadEnd=" + deadEnd +
                ", clue='" + clue + '\'' +
                '}';
    }
}
