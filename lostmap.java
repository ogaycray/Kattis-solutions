import java.util.*;
import java.io.*;

public class lostmap {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();

        List<Edge> edgelist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edgelist.add(new Edge(i, j, io.getInt()));
            }
        }

        Collections.sort(edgelist);
        //io.println(edgelist);

        UnionFind ufds = new UnionFind(n);
        for (int k = 0; k < edgelist.size(); k++) {
            Edge edge = edgelist.get(k);
            int f = edge.first;
            int s = edge.second;
            if (!ufds.isSameSet(f, s)) {
                ufds.unionSet(f, s);
                io.println((f + 1) + " " + (s + 1));
            }
        }

        io.close();
    }
}

class Edge implements Comparable<Edge> {
    public Integer first, second, weight;
  
    public Edge(Integer f, Integer s, Integer t) {
      this.first = f;
      this.second = s;
      this.weight = t;
    }
  
    public int compareTo(Edge o) {
      if (!this.weight.equals(o.weight))
        return this.weight - o.weight;
      else if (!this.first.equals(o.first))
        return this.first - o.first;
      else
        return this.second - o.second;
    }

    public String toString() { return this.first + " " + this.second + " " + this.weight; }
}

class UnionFind {                                              
    public int[] p;
    public int[] rank;
  
    public UnionFind(int N) {
      p = new int[N];
      rank = new int[N];
      for (int i = 0; i < N; i++) {
        p[i] = i;
        rank[i] = 0;
      }
    }
  
    public int findSet(int i) { 
      if (p[i] == i) return i;
      else {
        p[i] = findSet(p[i]);
        return p[i]; 
      } 
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
      if (!isSameSet(i, j)) { 
        int x = findSet(i), y = findSet(j);
        // rank is used to keep the tree short
        if (rank[x] > rank[y]) 
            p[y] = x;
        else { 
            p[x] = y;
          if (rank[x] == rank[y]) 
            rank[y] = rank[y]+1; 
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
