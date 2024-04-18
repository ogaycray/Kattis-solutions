import java.util.*;
import java.io.*;

public class weakvertices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();

        while (n != -1) {

            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = io.getInt();
                }
            }

            for (int r = 0; r < n; r++) {
                boolean weak = true;
                for (int q = 0; q < n; q++) {
                    if (matrix[r][q] == 1) {
                        for (int s = q + 1; s < n; s++) {
                            if (matrix[q][s] == 1 && matrix[s][r] == 1) {
                                weak = false;
                            }
                        }
                    }
                }
                if (weak) {
                    io.print(r + " ");
                }
            }
            io.println();
            n = io.getInt();
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