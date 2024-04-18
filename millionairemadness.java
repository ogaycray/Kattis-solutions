import java.io.*;
import java.util.*;

public class millionairemadness {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int rows = io.getInt();
        int columns = io.getInt();

        int[][] heights = new int[rows][columns];
        int[][] visited = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                heights[i][j] = io.getInt();
            }
        }        

        PriorityQueue<Coinstack> pq = new PriorityQueue<Coinstack>();
        pq.offer(new Coinstack(0, 0, heights[0][0], 0));
        visited[0][0] = 1;
        int ladder = 0;

        while (!pq.isEmpty() && visited[rows - 1][columns - 1] != 1) {
            Coinstack curr = pq.poll();
            visited[curr.x][curr.y] = 1;
            if (curr.climb > ladder) {
                ladder = curr.climb;
            }

            int[] up = {curr.x - 1, curr.y};
            int[] down = {curr.x + 1, curr.y};
            int[] left = {curr.x, curr.y - 1};
            int[] right = {curr.x, curr.y + 1};

            if (up[0] >= 0 && visited[up[0]][up[1]] != 1) {
                int newHeight = heights[up[0]][up[1]];
                pq.offer(new Coinstack(up[0], up[1], newHeight, newHeight - curr.height));
            }
            if (down[0] < rows && visited[down[0]][down[1]] != 1) {
                int newHeight = heights[down[0]][down[1]];
                pq.offer(new Coinstack(down[0], down[1], newHeight, newHeight - curr.height));
            }
            if (left[1] >= 0 && visited[left[0]][left[1]] != 1) {
                int newHeight = heights[left[0]][left[1]];
                pq.offer(new Coinstack(left[0], left[1], newHeight, newHeight - curr.height));
            }
            if (right[1] < columns && visited[right[0]][right[1]] != 1) {
                int newHeight = heights[right[0]][right[1]];
                pq.offer(new Coinstack(right[0], right[1], newHeight, newHeight - curr.height));
            }
        }

        io.print(ladder);
        io.close();
    }
}

class Coinstack implements Comparable<Coinstack>{
    public int x;
    public int y;
    public int height;
    public int climb;

    Coinstack(int x, int y, int height, int climb) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.climb = climb;
    }

    public int compareTo(Coinstack c) {
        return this.climb - c.climb;
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
