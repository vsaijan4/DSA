package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class mixmilk {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        int[] c = new int[3];
        int[] m = new int[3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bReader.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            m[i] = Integer.parseInt(st.nextToken());
        }
        int amt, a, b;
        for (int i = 0; i < 100; i++) {
            a = i % 3;
            b = (i + 1) % 3;
            amt = Math.min(m[a], c[b] - m[b]);
            m[a] -= amt;
            m[b] += amt;
        }
        for (int i = 0; i < 3; i++) {
            out.println(m[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("mixmilk");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}