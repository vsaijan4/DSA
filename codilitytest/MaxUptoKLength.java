package codilitytest;

import java.io.*;

public class MaxUptoKLength {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int k = Integer.parseInt(br.readLine());
        System.out.println(solution(s, k));
    }

    public static String solution(String message, int K) {
        int len = message.length();
        if (K >= len) return message;
        int index = K - 1;
        if (message.charAt(index) != ' ' && message.charAt(index + 1) == ' ')
            return message.substring(0, index + 1);
        while (index >= 0 && message.charAt(index) != ' ')
            index--;

        while (index >= 0 && message.charAt(index) == ' ')
            index--;
        return message.substring(0, index + 1);
    }
}
