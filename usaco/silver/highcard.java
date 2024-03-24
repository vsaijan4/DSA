package usaco.silver;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class highcard {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int n2 = n * 2;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i < n2; i++) {
            set.add(i);
        }
        set.add(n2);
        int[] card = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            int val = Integer.parseInt(st.nextToken());
            card[i] = val;
            set.remove(val);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int val = card[i];
            if (set.higher(val) != null) {
                set.remove(set.higher(val));
                ans++;
            } else {
                set.remove(set.first());
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("highcard");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}