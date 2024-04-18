import java.util.*;

public class sortofsorting {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean firstCase = true;
        ArrayList<String> sortedList = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            if (!firstCase) {
                sortedList.add(".");
            }
            int numOfNames = sc.nextInt();
            if (numOfNames == 0) {
                break;
            }
            String[] arr = new String[numOfNames];
            for (int j = 0; j < numOfNames; j++) {
                arr[j] = sc.next();
            }
            Arrays.sort(arr, new NameComparator());
            for (String s : arr) {
                sortedList.add(s);
            }
            firstCase = false;
        }
        for (String s : sortedList) {
            if (s == ".") {
                System.out.println();
                continue;
            }
            System.out.println(s);
        }
    }
}

class NameComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        String sub1 = s1.substring(0, 2);
        String sub2 = s2.substring(0, 2);
        return sub1.compareTo(sub2);
    }
}