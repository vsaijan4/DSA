package usaco.silver;

import java.io.*;
import java.util.StringTokenizer;

public class moocastDSU {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;
    static int[] parent;

    static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N];
        Point[] cow = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            cow[i] = new Point(x, y, p * p);
            parent[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++)
                if (isConnected(cow[i], cow[j]))
                    union(i, j);
        }
        int[] size = new int[N];
        for (int i = 0; i < N; i++) {
            size[find(i)]++;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (size[i] > ans)
                ans = size[i];
        }
        out.println(ans);
    }

    static boolean isConnected(Point a, Point b) {
        int x = a.x - b.x;
        int y = a.y - b.y;
        int dist = x * x + y * y;
        return dist <= Math.max(a.p, b.p);
    }

    static void union(int a, int b) {
        int c = find(a);
        int d = find(b);
        if (c != d) parent[d] = c;
    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    static class Point {
        int x, y, p;

        Point(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
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