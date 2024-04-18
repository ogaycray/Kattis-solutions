// Ho Ern See A0259624W

// collaborators: Chua Chong Han, Shawn,Zhang Leran, Kaushik Raman

import java.util.*;

public class t9spelling {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put(" ", "0");
        map.put("a", "2");
        map.put("b", "22");
        map.put("c", "222");
        map.put("d", "3");
        map.put("e", "33");
        map.put("f", "333");
        map.put("g", "4");
        map.put("h", "44");
        map.put("i", "444");
        map.put("j", "5");
        map.put("k", "55");
        map.put("l", "555");
        map.put("m", "6");
        map.put("n", "66");
        map.put("o", "666");
        map.put("p", "7");
        map.put("q", "77");
        map.put("r", "777");
        map.put("s", "7777");
        map.put("t", "8");
        map.put("u", "88");
        map.put("v", "888");
        map.put("w", "9");
        map.put("x", "99");
        map.put("y", "999");
        map.put("z", "9999");

        Scanner sc = new Scanner(System.in);
        int numOfCases = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= numOfCases; i++) {
            StringBuilder output = new StringBuilder();
            output.append("Case #").append(i).append(": ");
            String words = sc.nextLine();
            for (int j = 0; j < words.length(); j++) {
                String current = map.get(words.substring(j, j+1));
                if (current.substring(0, 1).equals(output.substring(output.length()-1))) {
                    output.append(" ");
                }
                output.append(current);
            }
            System.out.println(output);
        }

    }
}