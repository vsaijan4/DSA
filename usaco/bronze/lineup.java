package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

public class lineup {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;
    public static final String[] LIST = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
    public static int[][] constraints;
    public static int N;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        N = Integer.parseInt(st.nextToken());
        constraints = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            String a = st.nextToken();
            for (int j = 0; j < 4; j++) st.nextToken();
            String b = st.nextToken();
            constraints[i][0] = findIndexOf(a);
            constraints[i][1] = findIndexOf(b);
        }
        perm(8);
    }

    public static int findIndexOf(String str) {
        for (int k = 0; k < 8; k++)
            if (str.equals(LIST[k]))
                return k;
        return 0;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean hasNext(int[] a) {
        int n = a.length;
        int k;
        for (k = n - 2; k >= 0; k--)
            if (a[k] < a[k + 1]) break;
        if (k == -1) return false;
        int j = n - 1;
        while (a[k] > a[j]) j--;
        swap(a, j, k);
        for (int r = n - 1, s = k + 1; r > s; r--, s++)
            swap(a, r, s);
        return true;
    }

    public static void perm(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        if (isValid(a)) return;
        while (hasNext(a))
            if (isValid(a)) return;
    }

    public static boolean isValid(int[] a) {
        for (int i = 0; i < N; i++) {
            int m = constraints[i][0];
            int n = constraints[i][1];
            boolean satisfied = false;
            for (int j = 0; j < 8; j++) {
                if (a[j] == m) {
                    if ((j - 1 >= 0 && a[j - 1] == n) || (j + 1 < 8 && a[j + 1] == n)) {
                        satisfied = true;
                        break;
                    }
                }
            }
            if (!satisfied) return false;
        }
        for (int i = 0; i < 8; i++) {
            out.println(LIST[a[i]]);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        initializeSys("lineup");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}