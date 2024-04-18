//Ho Ern See A0259624W

import java.util.*;
import java.io.*;

public class cardtrading {
    public static void main(String args[]) {
        Kattio io = new Kattio(System.in);
        int numCards = io.getInt();
        int numTypes = io.getInt();
        int numCombos = io.getInt();

        int[] cardNumList = new int[numCards];
        for (int i = 0; i < numCards; i++) {
            cardNumList[i] = io.getInt();
        }

        int[] cardFreq = new int[numTypes];
        for (int n = 0; n < numTypes; n++) {
            cardFreq[n] = 0;
        }
        for (int num : cardNumList) {
            cardFreq[num - 1]++;
        }

        Card[] cards = new Card[numTypes];
        for (int j = 0; j < numTypes; j++) {
            long buy = io.getInt();
            long sell = io.getInt();
            int freq = cardFreq[j];
            cards[j] = new Card(j + 1, buy, sell, freq);
        }

        Arrays.sort(cards, new CardComp());
        
        long profit = 0;
        for (int k = 0; k < numCombos && k < numTypes; k++) {
            Card card = cards[k];
            if (card.getFreq() > 1) {
                continue;
            } else {
                profit -= card.getBuy() * (2 - card.getFreq());
            }
        }

        for (int q = numCombos; q < numTypes; q++) {
            Card card = cards[q];
            profit += (card.getSell() * card.getFreq());
        }

        System.out.println(profit);
        io.close();
    }
}

class Card {

    long buy;
    long sell;
    int num;
    int freq;

    Card(int num, long buy, long sell, int freq) {
        this.num = num;
        this.buy = buy;
        this.sell = sell;
        this.freq = freq;
    }

    int getNum() {
        return this.num;
    }

    long getBuy() {
        return this.buy;
    }

    long getSell() {
        return this.sell;
    }

    long getValue() {
        return this.buy * (2 - this.freq) + this.sell * this.freq;
    }

    int getFreq() {
        return this.freq;
    }
}

class CardComp implements Comparator<Card> {
    public int compare(Card c1, Card c2) {
        long value1 = c1.getValue();
        long value2 = c2.getValue();

      if (value1 == value2) {
            if (c1.getBuy() > c2.getBuy()) {
                return 1;
            } else if (c1.getBuy() < c2.getBuy()) {
                return -1;
            } else {
                return 0;
            }
        } else if (value1 > value2) {
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