import java.io.*;
import java.util.*;

public class islands {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int numRows = io.getInt();
        int numColumns = io.getInt();
        String[][] grid = new String[numRows][numColumns];
        int[][] visited = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            String row = io.getWord();
            String[] rowArr = row.split("");
            for (int j = 0; j < numColumns; j++) {
                grid[i][j] = rowArr[j];
            } 
        }

        int count = 0;
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                if (visited[x][y] == 0 && grid[x][y].equals("L")) {
                    count++;
                    dfs(x, y, grid, visited);
                }
            }
        }
        io.println(count);
        io.close();
    }

    static void dfs(int x, int y, String[][] grid, int[][] visited) {
        if (visited[x][y] == 0) {
            int[] up = {x - 1, y};
            int[] down = {x + 1, y};
            int[] left = {x, y - 1};
            int[] right = {x, y + 1};

            visited[x][y] = 1;

            if (x - 1 >= 0) {
                if (grid[up[0]][up[1]].equals("C") || grid[up[0]][up[1]].equals("L")) {
                    dfs(up[0], up[1], grid, visited);
                }  
            } 
            if (x + 1 < grid.length) {
                if (grid[down[0]][down[1]].equals("C") || grid[down[0]][down[1]].equals("L")) {
                    dfs(down[0], down[1], grid, visited);
                }  
            }
            if (y - 1 >= 0) {
                if (grid[left[0]][left[1]].equals("C") || grid[left[0]][left[1]].equals("L")) {
                    dfs(left[0], left[1], grid, visited);
                }  
            }
            if (y + 1 < grid[0].length) {
                if (grid[right[0]][right[1]].equals("C") || grid[right[0]][right[1]].equals("L")) {
                    dfs(right[0], right[1], grid, visited);
                }  
            }
        }
        
    }
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
