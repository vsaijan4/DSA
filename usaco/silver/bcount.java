package usaco.silver;

import java.io.*;
import java.util.StringTokenizer;

public class bcount {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] H = new int[N + 1];
        int[] G = new int[N + 1];
        int[] J = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bReader.readLine());
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    H[i] = 1;
                    break;
                case 2:
                    G[i] = 1;
                    break;
                default:
                    J[i] = 1;
            }
            H[i] += H[i - 1];
            G[i] += G[i - 1];
            J[i] += J[i - 1];
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bReader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());
            out.println((H[b] - H[a]) + " " + (G[b] - G[a]) + " " + (J[b] - J[a]));
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("bcount");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}