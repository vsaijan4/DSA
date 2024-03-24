package books;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KnapsackSubsetSumRecursive {
    static final OutputWriter out = new OutputWriter(System.out);
    static int[] a;
    static ArrayList<Integer> ans;

    public static void main(String[] args) {
        a = new int[]{3, 34, 4, 12, 5, 2};
        int sum = 9;
        ans = new ArrayList<>();
        if (knapsack(0, sum)) {
            out.printLine("True");
            for (int i : ans) {
                out.print(i + " ");
            }
        } else {
            out.printLine("False");
        }
        out.close();
    }

    static boolean knapsack(int i, int sum) {
        out.printLine("i: " + i + " sum: " + sum);
        if (sum == 0) return true;
        if (i == a.length) return false;
        if (a[i] <= sum) {
            if (knapsack(i + 1, sum - a[i])) {
                ans.add(a[i]);
                return true;
            }
        }
        return knapsack(i + 1, sum);
    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    outputStream)));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }
    }
}
