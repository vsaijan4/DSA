package usaco.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class moop {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;

    static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        Point[] cow = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cow[i] = new Point(x, y);
        }
        Arrays.sort(cow);
        int[] minLeft = new int[N];
        int[] maxRight = new int[N];

        minLeft[0] = cow[0].y;
        for (int i = 1; i < N; i++)
            minLeft[i] = Math.min(minLeft[i - 1], cow[i].y);

        maxRight[N - 1] = cow[N - 1].y;
        for (int i = N - 2; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], cow[i].y);

        int ans = 1;
        for (int i = 0; i < N - 1; i++) {
            if (minLeft[i] > maxRight[i + 1])
                ans++;
        }
        out.println(ans);
    }

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point b) {
            if (x == b.x)
                return Integer.compare(y, b.y);
            return Integer.compare(x, b.x);
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("moop");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}