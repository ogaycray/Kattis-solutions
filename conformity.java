import java.util.*;
import java.io.*;

public class conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        int highest = 0;
        int highestCount = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                list.add(io.getInt());
            }
            Collections.sort(list);
            if (map.containsKey(list)) {
                int value = map.get(list);
                value++;
                map.put(list, value);
                if (value > highest) {
                    highest = value;
                    highestCount = 1;
                } else if (value == highest) {
                    highestCount++;
                }
            } else {
                map.put(list, 1);
                highest = highest == 0 ? 1 : highest;
                if (highest == 1) {
                    highestCount++;
                }
            }
        }
        io.print(highestCount*highest);
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
