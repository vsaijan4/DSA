package usaco.bronze;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mountains {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;
    public static int[] sum;
    public static int[] diff;

    static class Comp implements Comparable<Comp> {
        int idx;

        public Comp(int i) {
            this.idx = i;
        }

        public int compareTo(Comp p) {
            if (diff[idx] == diff[p.idx])
                return Integer.compare(sum[p.idx], sum[idx]);
            return Integer.compare(diff[idx], diff[p.idx]);
        }
    }

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        Comp[] index = new Comp[n];
        sum = new int[n];
        diff = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sum[i] = x + y;
            diff[i] = x - y;
            index[i] = new Comp(i);
        }
        Arrays.sort(index);
        int maxSum = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (sum[index[i].idx] > maxSum) {
                maxSum = sum[index[i].idx];
                ans++;
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("mountains");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}