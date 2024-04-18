import java.util.*;
import java.io.*;

public class kattisquest {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        long n = io.getInt();
        TreeSet<Quest> treeset = new TreeSet<>(new QuestComp());
        for (int i = 0; i < n; i++) {
            String command = io.getWord();
            if (command.equals("add")) {
                treeset.add(new Quest(i, io.getInt(), io.getLong()));
            } else if (command.equals("query")) {
                long goldsum = 0;
                long energylevel = io.getInt();
                while (energylevel > 0 && !treeset.isEmpty()) {
                    Quest quest = treeset.floor(new Quest(0, energylevel, 100001));
                    if (quest != null) {
                        energylevel -= quest.energy;
                        goldsum += quest.gold;
                        treeset.remove(quest);
                    } else {
                        break;
                    }
                }
            io.println(goldsum);
            }
        }
        io.close();
    }
}

class Quest {
    public int id;
    public long energy;
    public long gold;
    Quest(int id, long energy, long gold) {
        this.id = id;
        this.energy = energy;
        this.gold = gold;
    }
}

class QuestComp implements Comparator<Quest> {
    public int compare(Quest q1, Quest q2) {
        if (q1.energy == q2.energy) {
            if (q1.gold == q2.gold) {
                return q1.id - q2.id;
            } else if (q1.gold > q2.gold) {
                return 1;
            } else {
                return -1;
            }
        } else if (q1.energy > q2.energy) {
            return 1;
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
