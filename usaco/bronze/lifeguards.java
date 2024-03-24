package usaco.bronze;
import java.io.*;
import java.util.StringTokenizer;

public class lifeguards {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] t = new int[n][2];
        int tLen = 0;
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            t[i][0] = Integer.parseInt(st.nextToken());
            t[i][1] = Integer.parseInt(st.nextToken());
            if (t[i][1] > tLen) tLen = t[i][1];
        }
        tLen++;
        for (int i = 0; i < n; i++) {
            boolean[] covered = new boolean[tLen];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = t[j][0]; k < t[j][1]; k++)
                    covered[k] = true;
            }
            int time = 0;
            for (int p = 0; p < tLen; p++)
                if (covered[p]) time++;
            if (maxTime < time) maxTime = time;
        }
        out.println(maxTime);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("lifeguards");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}