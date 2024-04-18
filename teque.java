import java.util.*;
import java.io.*;

public class teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());

        int[] frontArr = new int[10000000];
        int[] backArr = new int[10000000];

        int frontSize = 0;
        int backSize = 0;
        int frontPointer = 4999999;
        int backPointer = 4999999;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            if ("push_back".equals(command)) {
                if (backSize > frontSize) {
                    frontArr[frontPointer] = backArr[backPointer + 1];
                    backArr[backPointer + backSize + 1] = x;
                    frontSize++;
                    frontPointer++;
                    backPointer++;
                } else {
                    backArr[backPointer + backSize + 1] = x;
                    backSize++;
                }
                
            } else if ("push_front".equals(command)) {
                if (frontSize > backSize) {
                    backArr[backPointer] = frontArr[frontPointer - 1];
                    frontArr[frontPointer - frontSize - 1] = x;
                    frontPointer--;
                    backPointer--;
                    backSize++;
                } else {
                    frontArr[frontPointer - frontSize - 1] = x;
                    frontSize++;
                }
                
            } else if ("push_middle".equals(command)) {
                if (frontSize == backSize) {
                    frontArr[frontPointer] = x;
                    frontPointer++;
                    frontSize++;
                } else if (frontSize < backSize) {
                    frontArr[frontPointer] = backArr[backPointer + 1];
                    backArr[backPointer + 1] = x;
                    frontPointer++;
                    frontSize++;
                } else {
                    backArr[backPointer] = x;
                    backSize++;
                    backPointer--;
                }
                
            } else if ("get".equals(command)) {
                if (x < frontSize) {
                    pw.println(frontArr[frontPointer - frontSize + x]);
                } else {
                    pw.println(backArr[x - frontSize + 1 + backPointer]);
                }
            }
        }
        pw.close();
    }
}
