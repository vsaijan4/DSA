package hackerrank;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class ResultGA {

    /*
     * Complete the 'getGroupedAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY words as parameter.
     // Write your code here
        // O(N) * O(fn(word length))
     */

    public static int getGroupedAnagrams(List<String> words) {
        Set<String> codeSet = new HashSet<>();
        for (String word : words) {
            codeSet.add(getCode(word));
        }
        return codeSet.size();
    }

    public static String getCode(String s) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            sb.append(count[i]);
        }
        return sb.toString();
    }

}


public class GroupAnagrams {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int wordsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, wordsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());

        int result = ResultGA.getGroupedAnagrams(words);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}