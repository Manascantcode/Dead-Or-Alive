//WUMPUS WORLD NEXT
import java.awt.*;
import java.util.*;
import javax.swing.*;
import p1.Player;

// ---------------------- MAP CONFIG -----------------------
class MapConfig {
    public int n;
    public int[][] trapPositions; // array of [x,y]
    public int[] exitPosition;    // [x,y]
    public String[][] clues;      // clues per room

    public MapConfig(int n, int[][] traps, int[] exit, String[][] clues) {
        this.n = n;
        this.trapPositions = traps;
        this.exitPosition = exit;
        this.clues = clues;
    }
}

// ---------------------- GAME CLASS ----------------------
public class CubeLogicMazeGame extends JFrame {
    private RoomCube[][] maze;
    private int n;
    private Player player = new Player();
    private MazePanel gridPanel;
    private JLabel playerStats;

    public CubeLogicMazeGame(MapConfig map) {
        super("Cube Maze – Alice in Borderland Mode");
        this.n = map.n;
        this.maze = new RoomCube[n][n];
        generateMaze(map);
        gridPanel = new MazePanel();

        JPanel rightPanel = new JPanel(new BorderLayout());
        playerStats = new JLabel(player.statusString());
        rightPanel.add(playerStats, BorderLayout.NORTH);

        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(controlPanel(), BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(n * 100 + 200, n * 100 + 150);
        setLocationRelativeTo(null);
        setVisible(true);
        showDisclaimer();
        updateGrid();
    }

    // --------------------- MAZE SETUP ----------------------
    private void generateMaze(MapConfig map) {
        int num = 1;
        for (int xx = 0; xx < n; xx++) {
            for (int yy = 0; yy < n; yy++) {
                maze[xx][yy] = new RoomCube(false, false, num++);
            }
        }

        // Set traps
        for (int[] pos : map.trapPositions) {
            maze[pos[0]][pos[1]].isTrap = true;
        }

        // Set exit
        maze[map.exitPosition[0]][map.exitPosition[1]].isExit = true;

        // Set clues
        for (int xx = 0; xx < n; xx++) {
            for (int yy = 0; yy < n; yy++) {
                maze[xx][yy].clue = (map.clues[xx][yy] != null) ? map.clues[xx][yy] : "";
            }
        }
    }

    private void showDisclaimer() {
        JOptionPane.showMessageDialog(this,
            "⚠️ WELCOME TO THREE OF CLUBS ⚠️\n              ☠️DEAD or ALIVE☠️ \n\n"
          + "You start in Room #1.\n"
          + "Each room has two doors – one marked 'Life' and one 'Death'.\n"
          + "Only one leads you to safety.\n"
          + "Some doors lie.\n"
          + "Good luck... Choose wisely."
        );
    }

    // -------------------- MOVEMENT LOGIC -------------------
    private JPanel controlPanel() {
        JPanel panel = new JPanel();
        JButton moveButton = new JButton("Next Room");
        moveButton.addActionListener(e -> nextMove());
        panel.add(moveButton);
        return panel;
    }

    private void nextMove() {
        int x = player.x;
        int y = player.y;
        RoomCube current = maze[x][y];

        // Find all adjacent unvisited rooms (orthogonal only)
        ArrayList<Point> options = new ArrayList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // right, down, left, up
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && !maze[nx][ny].visited) {
                options.add(new Point(nx, ny));
            }
        }

        if (options.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No further rooms. Game Over.");
            System.exit(0);
        }

        // If more than 2 options, pick 2 randomly
        Collections.shuffle(options);
        if (options.size() > 2) {
            options = new ArrayList<>(options.subList(0, 2));
        }

        // Show next room numbers
        StringBuilder info = new StringBuilder("From Room #" + current.roomNumber + ", you can move to:\n");
        for (Point p : options) {
            info.append("→ Room #").append(maze[p.x][p.y].roomNumber).append("\n");
        }
        JOptionPane.showMessageDialog(this, info.toString());

        // Life/Death door logic with clue
        for (Point nextP : options) {
            RoomCube nextRoom = maze[nextP.x][nextP.y];

            // Clue
            String clueMsg = !nextRoom.clue.isEmpty() ? "Clue: " + nextRoom.clue + "\n\n" : "";

            // Life/Death label
            String mark = nextRoom.isTrap ? "DEATH" : "LIFE";
            boolean reversed = new Random().nextBoolean(); // random lie
            if (reversed) mark = mark.equals("LIFE") ? "DEATH" : "LIFE";

            String msg = clueMsg + "This door is marked '" + mark + "'.\nDo you want to enter Room #" + nextRoom.roomNumber + "?";
            int choice = JOptionPane.showConfirmDialog(this, msg, "Door Decision", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                processRoom(nextP.x, nextP.y, nextRoom);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "You refused both doors. Game Over.");
        System.exit(0);
    }

    private void processRoom(int newX, int newY, RoomCube next) {
        player.moveTo(newX, newY, 0);
        next.visited = true;
        updateGrid();

        if (next.isExit) {
            JOptionPane.showMessageDialog(this, 
                "Congratulations 🎉 You reached the EXIT door! You survived!");
            System.exit(0);
        } else if (next.isTrap) {
            next.deadEnd = true;
            player.deathScore++;
            updateGrid();
            JOptionPane.showMessageDialog(this, 
                "☠️ Room #" + next.roomNumber + " was a TRAP. You are dead!");
            System.exit(0);
        } else {
            player.lifeScore++;
            updateGrid();
            JOptionPane.showMessageDialog(this, 
                "💖 You safely entered Room #" + next.roomNumber + ".");
        }
    }

    // -------------------- GUI UPDATE -----------------------
    private void updateGrid() {
        maze[player.x][player.y].visited = true;
        gridPanel.repaint();
        playerStats.setText(player.statusString());
    }

    class MazePanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int size = Math.min(getWidth(), getHeight()) / n;
            for (int xx = 0; xx < n; xx++) {
                for (int yy = 0; yy < n; yy++) {
                    RoomCube room = maze[xx][yy];
                    if (xx == player.x && yy == player.y)
                        g.setColor(Color.ORANGE);       // Player
                    else if (room.isExit)
                        g.setColor(Color.WHITE);        // Exit
                    else if (room.isTrap)
                        g.setColor(Color.WHITE);        // Trap
                    else if (room.visited)
                        g.setColor(Color.CYAN);         // Visited safe
                    else
                        g.setColor(Color.WHITE);        // Unvisited

                    g.fillRect(yy * size, xx * size, size, size);
                    g.setColor(Color.BLACK);
                    g.drawRect(yy * size, xx * size, size, size);
                    g.drawString("" + room.roomNumber, yy * size + 10, xx * size + size / 2);
                }
            }
        }
    }

    // -------------------- MAIN -----------------------------
    public static void main(String[] args) {

    // First map
    int[][] traps1 = {{0,1}, {1,1}};
    int[] exit1 = {0,2};
    String[][] clues1 = new String[3][3];    
    clues1[0][1] = "Beware! Danger is nearby.";             
    clues1[0][2] = "Exit is ahead!";                        
    clues1[1][0] = "Choose wisely to survive.";             
    clues1[1][1] = "Not all doors lie.";                    
    clues1[1][2] = "Trust your instincts.";                 
    clues1[2][0] = "Life hides in unexpected rooms.";       
    clues1[2][1] = "One door leads on, the other ends it."; 
    clues1[2][2] = "You are close to the exit.";            
    MapConfig map1 = new MapConfig(3, traps1, exit1, clues1);

    // Second map
    int[][] traps2 = {{0,1}, {0,2} ,{2,0} ,{2,1}}; // New trap positions
    int[] exit2 = {2,2};             // New exit
    String[][] clues2 = new String[3][3];
    clues1[0][1] =  "One door leads on, the other ends it.";            
    clues1[0][2] = "Exit is ahead!";                        
    clues1[1][0] = "Choose wisely to survive.";             
    clues1[1][1] = "Not all doors lie.";                    
    clues1[1][2] = "Life hides in unexpected rooms.";                  
    clues1[2][0] =  "Trust your instincts.";     
    clues1[2][1] = "Let's go!You are very close.";
    clues2[2][2] = "Almost there!";
    MapConfig map2 = new MapConfig(3, traps2, exit2, clues2);

    String[] options = {"Map 1 – Beginner", "Map 2 – Advanced"};
    int choice = JOptionPane.showOptionDialog(
        null,
        "Select a map to begin your journey:",
        "Cube Maze Map Selection",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]
    );

    MapConfig selectedMap;
    if (choice == 1) {
        selectedMap = map2;
    } else {
        selectedMap = map1;
    }

    // -------- START GAME --------
    SwingUtilities.invokeLater(() -> new CubeLogicMazeGame(selectedMap));
}


}
