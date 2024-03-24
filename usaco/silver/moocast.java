package usaco.silver;

import java.io.*;
import java.util.StringTokenizer;

public class moocast {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;
    static int N;
    static boolean[][] adjMatrix;
    static boolean[] visited;

    static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        N = Integer.parseInt(st.nextToken());
        adjMatrix = new boolean[N][N];
        Point[] cow = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            cow[i] = new Point(x, y, p * p);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cow[i].isReachable(cow[j]))
                    adjMatrix[i][j] = true;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            int size = dfs(i);
            if (size > ans) ans = size;
        }
        out.println(ans);
    }

    static int dfs(int node) {
        if (visited[node]) return 0;
        visited[node] = true;
        int size = 1;
        for (int i = 0; i < N; i++) {
            if (adjMatrix[node][i]) {
                size += dfs(i);
            }
        }
        return size;
    }

    static class Point {
        int x, y, p;

        Point(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }

        boolean isReachable(Point b) {
            int dx = x - b.x;
            int dy = y - b.y;
            return (dx * dx + dy * dy) <= p;
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("moocast");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}