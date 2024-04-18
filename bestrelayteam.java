// Ho Ern See A0259624W
import java.util.*;

public class bestrelayteam {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int numOfRunners = sc.nextInt();
        List<Runner> runners = new ArrayList<>();
        for (int i = 0; i < numOfRunners; i++) {
            sc.nextLine();
            runners.add(new Runner(sc.next(), sc.nextDouble(), sc.nextDouble()));
        }
        Runner r1 = new Runner();
        Runner r2 = new Runner();
        Runner r3 = new Runner();
        Runner r4 = new Runner();
        double totalTime = 10000.00;
        List<Runner> runnersCopy = new ArrayList<>(runners);
        for (int j = 0; j < runners.size(); j++) {
            Runner temp = runners.get(j);
            runnersCopy.remove(temp);
            runnersCopy.sort(new OtherTimeComp());
            double currentTotalTime = getTotalTime(temp, runnersCopy.get(0), runnersCopy.get(1), runnersCopy.get(2));
            if (currentTotalTime < totalTime) {
                totalTime = currentTotalTime;
                r1 = temp;
                r2 = runnersCopy.get(0);
                r3 = runnersCopy.get(1);
                r4 = runnersCopy.get(2);
            }
            runnersCopy.add(temp);
        }
        System.out.println(totalTime);
        System.out.println(r1.getName());
        System.out.println(r2.getName());
        System.out.println(r3.getName());
        System.out.println(r4.getName());
    }

    static double getTotalTime(Runner r1, Runner r2, Runner r3, Runner r4) {
        return r1.getFirst() + r2.getOthers() + r3.getOthers() + r4.getOthers();
    }
}

class Runner {
    double first;
    double others;
    String name;

    Runner() {
        this.name = "";
        this.first = 0.0;
        this.others = 0.0;
    }

    Runner(String name, double first, double others) {
        this.name = name;
        this.first = first;
        this.others = others;
    }

    public double getFirst() {
        return this.first;
    }

    public double getOthers() {
        return this.others;
    }

    public String getName() {
        return this.name;
    }
}

class OtherTimeComp implements Comparator<Runner> {
    public int compare(Runner r1, Runner r2) {
        double diff = r1.getOthers() - r2.getOthers();
        return Double.compare(diff, 0.0);
    }
}

class FirstTimeComp implements Comparator<Runner> {
    public int compare(Runner r1, Runner r2) {
        double diff = r1.getFirst() - r2.getFirst();
        return Double.compare(diff, 0.0);
    }
}
