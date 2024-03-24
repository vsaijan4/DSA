package usaco.bronze;
import java.io.*;
import java.util.StringTokenizer;

public class gymnastics {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] rank = new int[k][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bReader.readLine());
            for (int j = 0; j < n; j++) {
                rank[i][Integer.parseInt(st.nextToken()) - 1] = j;
            }
        }
        int pairs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int p = 1;
                if (rank[0][i] < rank[0][j]) {
                    while (p < k) {
                        if (rank[p][i] > rank[p][j])
                            break;
                        p++;
                    }
                } else {
                    while (p < k) {
                        if (rank[p][i] < rank[p][j])
                            break;
                        p++;
                    }
                }
                if (p == k) {
                    pairs++;
                }
            }
        }
        out.println(pairs);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("gymnastics");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}