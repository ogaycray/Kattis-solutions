// Ho Ern See A0259624W

import java.io.*;
import java.util.*;

public class humancannonball {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        Coords a = new Coords(io.getDouble(), io.getDouble());
        Coords z = new Coords(io.getDouble(), io.getDouble());
        int numCannons = io.getInt();
        ArrayList<Coords> coordinates = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>();

        // add all coordinates into list
        coordinates.add(a);
        for (int i = 0; i < numCannons; i++) {
            coordinates.add(new Coords(io.getDouble(), io.getDouble()));
        }
        coordinates.add(z);

        // creating edges with their respective weights (time) and adding to edgelist
        boolean first = true;
        for (int i = 0; i < numCannons + 2; i++) {
            for (int j = 0; j < numCannons + 2; j++) {
                Coords start = coordinates.get(i);
                Coords end = coordinates.get(j);
                double distance = start.distanceTo(end);

                if (first) { // if i == 0, need to walk to first cannon from starting point
                    edgeList.add(new Edge(start, end, distance / 5.0));
                } else {
                    double time = 2.0;
                    if (distance != 50) {
                        time += (Math.abs(distance - 50) / 5.0);
                    }
                    edgeList.add(new Edge(start, end, time));
                }
            }
            first = false;
        }

        double[] t = new double[numCannons + 2]; // to store the shortest time which that coordinate can be reached from the source
        double infinity = Math.pow(10, 10);
        for (int i = 1; i < numCannons + 2; i++) {
            t[i] = infinity;
        }
        t[0] = 0;

        // finding shortest path
        for (int i = 0; i < numCannons + 2; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                relax(edgeList.get(j), edgeList, coordinates, t);
            }
        }

        io.println(t[numCannons + 1]);
        io.close();
    }

    static void relax(Edge e, ArrayList<Edge> edgeList, ArrayList<Coords> coordinates, double[] t) {
        if (t[coordinates.indexOf(e.start)] + e.time < t[coordinates.indexOf(e.end)]) {
            t[coordinates.indexOf(e.end)] = t[coordinates.indexOf(e.start)] + e.time;
        }
    }
}

class Edge {
    Coords start, end;
    double time;
    Edge(Coords start, Coords end, double time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}

class Coords {
    double x, y;
    Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distanceTo(Coords c) {
        double vert = Math.abs(this.y - c.y);
        double horiz = Math.abs(this.x - c.x);
        return Math.hypot(vert, horiz);
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
