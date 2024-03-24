package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

public class pails {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int yMax = m / y;
        int ans = 1000;
        for (int currY = 0; currY <= yMax; currY++) {
            int bal = (m - currY * y) % x;
            if (bal < ans)
                ans = bal;
        }
        out.println(m - ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("pails");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}