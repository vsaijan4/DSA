package usaco.silver;

import java.io.*;
import java.util.StringTokenizer;

public class reststops {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        st.nextToken();
        int n = Integer.parseInt(st.nextToken());
        int rf = Integer.parseInt(st.nextToken());
        int rb = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] isMax = new boolean[n];
        int cMax = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (c[i] > cMax) {
                isMax[i] = true;
                cMax = c[i];
            }
        }
        long ans = 0;
        int lastx = 0;
        int diff = rf - rb;
        for (int i = 0; i < n; i++)
            if (isMax[i]) {
                int dist = x[i] - lastx;
                ans += (long) dist * diff * c[i];
                lastx = x[i];
            }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("reststops");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}