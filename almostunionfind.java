import java.util.*;
import java.io.*;

public class almostunionfind {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        
        while (io.hasMoreTokens()) {

            int n = io.getInt();
            int m = io.getInt();
            UnionFind set = new UnionFind(n);

            for (int i = 0; i < m; i++) {
                int command = io.getInt();
                if (command == 1) {
                    set.unionSet(io.getInt(), io.getInt());
                } 
                else if (command == 2) {
                    set.add(io.getInt(), io.getInt());
                } else {
                    int x = io.getInt();
                    io.println(set.count[set.findSet(x)] + " " + set.sum[set.findSet(x)]);
                }
            }
        }
        io.close();
    }
}

class UnionFind {                                              
    public int[] p;
    public long[] sum;
    public int[] count;
    public int[] moved; // the new parent if it is moved
    
    public UnionFind(int N) {
        p = new int[N + 1];
        sum = new long[N + 1];
        count = new int[N + 1];
        moved = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            this.p[i] = i;
            this.sum[i] = i;
            this.count[i] = 1;
            this.moved[i] = -1;
        }
    }

    public int findSet(int i) {
        if (moved[i] > 0) { // finding the new parent after moved
            return findSet2(moved[i]); 
        } else if (moved[i] == 0) { // finding new parent after moved
            return findSet2(p[i]);
        } else {
            if (p[i] != i) {
                p[i] = findSet2(p[i]);
            }
            return p[i];
        }
    }

    public int findSet2(int i) {
        if (moved[i] > 0) { // moved parent has new parent, but continues searching with the old parent for old children
            return findSet2(p[i]);
        } else if (moved[i] == 0) { // moved parent has new parent, but is ultparent of children
            return i;
        } else {
            if (p[i] != i) {
                p[i] = findSet2(p[i]);
            }
            return p[i];
        }
    }

    public Boolean isSameSet(int i, int j) { return this.findSet(i) == this.findSet(j); }

    public void unionSet(int i, int j) { 
        int x = findSet(i);
        int y = findSet(j);
        if (x != y) {
            if (moved[x] == 0) {
                moved[x] = p[x];
            }
            
            p[x] = y;
            sum[y] += sum[x];
            count[y] += count[x];
        }
    }

    public void add(int i, int j) {
        if (!this.isSameSet(i, j)) {
            int x = findSet(i);
            int y = findSet(j);

            if (x == i) { // if x is ultparent, childparent is still i
                moved[i] = 0; 
                p[i] = y; // parent of x is changed
            } else if (y == i) { // if ult parent was moved and is being added back into original set
                moved[i] = -1;
                p[i] = y;
            } else if (moved[i] == 0) { // if moved parent is being moved again
                p[i] = y;
            } else {
                moved[i] = y; // if x not parent, moved[i] is new parent, p[i] same for children
            }
            sum[x] -= i;
            count[x]--;
            sum[y] += i;
            count[y]++;
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
