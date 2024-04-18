// Ho Ern See A0259624W

import java.io.*;
import java.util.*;

public class dominoes {

    static void dfs(int x, ArrayList<ArrayList<Integer>> adjList, int[] visited) { // normal dfs
        visited[x] = 1;
        for (int i = 0; i < adjList.get(x).size(); i++) {
            int next = adjList.get(x).get(i);
            if (visited[next - 1] != 1) {
                dfs(next - 1, adjList, visited);
            }
        }
    }

    static void dfs(Stack<Integer> stack, int x, ArrayList<ArrayList<Integer>> adjList, int[] visited) { // dfs for topo sort
        visited[x] = 1;
        for (int i = 0; i < adjList.get(x).size(); i++) {
            int next = adjList.get(x).get(i);
            if (visited[next - 1] != 1) {
                dfs(stack, next - 1, adjList, visited);
            }
        }
        stack.push(x);
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int numTestcases = io.getInt();

        for (int i = 0; i < numTestcases; i++) { // go through all the test cases
            int numTiles = io.getInt();
            int numLinks = io.getInt();
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            int[] visited = new int[numTiles];

            for (int j = 0; j < numTiles; j++) { // creating empty adjlist
                adjList.add(new ArrayList<>());
            }

            for (int k = 0; k < numLinks; k++) { // adding links to the adjlist
                int first = io.getInt();
                int second = io.getInt();
                if (first != second) {
                    adjList.get(first - 1).add(second);
                }
            }

            int count = 0;
            Stack<Integer> stack = new Stack<>(); // for topo sort array
            for (int r = 0; r < numTiles; r++) { // topo sort
                if (visited[r] != 1) {
                    dfs(stack, r, adjList, visited);
                }
            }

            int[] visited2 = new int[numTiles]; // new visited array for counting SCCs

            while (!stack.isEmpty()) { // count the SCCs
                int x = stack.pop();
                if (visited2[x] != 1) {
                    dfs(x, adjList, visited2);
                    count++; // if not connected by any other dominos, +1
                }
            }
            io.println(count);
        }
        io.close();
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
