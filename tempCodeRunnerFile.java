import java.awt.*;
import java.util.*;
import javax.swing.*;
import p1.Player;

class CubeLogicMazeGame extends JFrame {
    private RoomCube[][] maze;
    private int n;
    private Player player = new Player();
    private MazePanel gridPanel;
    private JLabel playerStats;

    public CubeLogicMazeGame(int n) {
        super("Cube Maze – Alice in Borderland Mode");
        this.n = n;
        this.maze = new RoomCube[n][n];
        generateMaze();
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

    // --------------------- Maze setup ------------------------
    private void generateMaze() {
        int num = 1;
        for (int xx = 0; xx < n; xx++) {
            for (int yy = 0; yy < n; yy++) {
                maze[xx][yy] = new RoomCube(false, false, num++);
            }
        }

        // Fixed traps
        maze[0][1].isTrap = true;  // Room 2
        maze[1][1].isTrap = true;  // Room 5

        // Fixed exit
        maze[0][2].isExit = true;  // Room 3
    }

    private void showDisclaimer() {
        JOptionPane.showMessageDialog(this,