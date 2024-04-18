import java.util.*;
import java.io.*;

public class workstations {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        int m = io.getInt();
        PriorityQueue<Integer> stations = new PriorityQueue<>();
        PriorityQueue<Researcher> researchers = new PriorityQueue<>(new ResearcherComparator());

        for (int i = 0; i < n; i++) {
            researchers.add(new Researcher(io.getInt(), io.getInt()));
        }

        int count = 0;

        for (int j = 0; j < n; j++) {

            Researcher r = researchers.poll();

            while (!stations.isEmpty() && stations.peek() + m < r.arrive) {
                stations.poll();
            }

            if (!stations.isEmpty()) {
                int window = r.arrive - stations.peek();
                if (window <= m && window >= 0) {
                    stations.poll();
                    count++;
                }
            }

            stations.add(r.getLeaveTime());
        }
        
        io.println(count);
        io.close();
    }
}

class Researcher {
    int arrive;
    int duration;

    Researcher(int arrive, int duration) {
        this.arrive = arrive;
        this.duration = duration;
    }

    int getLeaveTime() {
        return this.arrive + this.duration;
    }
}

class ResearcherComparator implements Comparator<Researcher> {
    public int compare(Researcher r1, Researcher r2) {
        if (r1.arrive > r2.arrive) {
            return 1;
        } else if (r1.arrive == r2.arrive) {
            return 0;
        } else {
            return -1;
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
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
