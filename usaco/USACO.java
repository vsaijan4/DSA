package usaco;

import java.io.*;
import java.util.StringTokenizer;

class USACO {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            a = Integer.parseInt(st.nextToken());
        }
        out.println();
    }

    public static void main(String[] args) throws IOException {
        initializeSys("");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}