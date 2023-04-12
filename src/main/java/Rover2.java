import java.util.*;

public class Rover2 {

    // Define the size of the terrain matrix
    private static final int ROWS = 8;
    private static final int COLS = 8;

    // Define the starting and ending positions
    private static final int START_ROW = 0;
    private static final int START_COL = 0;
    private static final int END_ROW = 7;
    private static final int END_COL = 7;

    // Define the blocked positions
    private static final int[][] BLOCKED = {{0, 2}, {2, 0}, {4, 4}, {4, 5}};

    // Define the directions that the rover can move in
    private static final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) {
        int[][] terrain = new int[ROWS][COLS];
        for (int[] pos : BLOCKED) {
            terrain[pos[0]][pos[1]] = 1;
        }
        List<int[]> route = findShortestPath(terrain);
        if (route != null) {
            System.out.println("Shortest path:");
            for (int[] pos : route) {
                System.out.printf("[%d][%d] -> ", pos[0], pos[1]);
            }
            System.out.printf("[%d][%d]\n", END_ROW, END_COL);
        } else {
            System.out.println("No path found.");
        }
    }

    private static List<int[]> findShortestPath(int[][] terrain) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, int[]> parent = new HashMap<>();
        queue.offer(new int[]{START_ROW, START_COL});
        visited.add(getKey(START_ROW, START_COL));
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == END_ROW && curr[1] == END_COL) {
                return buildRoute(parent, curr);
            }
            for (int[] dir : DIRS) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (nextRow >= 0 && nextRow < ROWS && nextCol >= 0 && nextCol < COLS &&
                        terrain[nextRow][nextCol] == 0 && !visited.contains(getKey(nextRow, nextCol))) {
                    queue.offer(new int[]{nextRow, nextCol});
                    visited.add(getKey(nextRow, nextCol));
                    parent.put(getKey(nextRow, nextCol), curr);
                }
            }
        }
        return null;
    }

    private static List<int[]> buildRoute(Map<String, int[]> parent, int[] curr) {
        List<int[]> route = new ArrayList<>();
        while (curr != null) {
            route.add(0, curr);
            curr = parent.get(getKey(curr[0], curr[1]));
        }
        return route;
    }

    private static String getKey(int row, int col) {
        return row + "," + col;
    }
}
