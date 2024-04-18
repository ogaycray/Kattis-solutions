import java.util.*;

public class coconut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSyllables = sc.nextInt();
        int numPlayers = sc.nextInt();
        List<Hand> list = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            list.add(new Hand(1, i + 1));
        }

        int currindex = 0;
        while (list.size() != 1) {
            currindex = (currindex + numSyllables - 1) % list.size();
            Hand hand = list.get(currindex);
            if (hand.getState() == 1) {
                hand.setState(2);
                list.add(currindex + 1, new Hand(2, hand.getPlayer()));
            } else if (hand.getState() == 2) {
                hand.setState(3);
                currindex++;
            } else if (hand.getState() == 3) {
                list.remove(hand);
            }
        }
        System.out.println(list.get(0).getPlayer());
    }
}

class Hand {
    int player;
    int state;

    Hand(int state, int player) {
        this.state = state;
        this.player = player;
    }

    int getPlayer() {
        return this.player;
    }

    int getState() {
        return this.state;
    }

    void setState(int state) {
        this.state = state;
    }
}