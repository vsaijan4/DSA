package usaco.bronze;
import java.io.*;
import java.util.StringTokenizer;

public class reduce {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] point = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }
        int minArea = Integer.MAX_VALUE;
        int gminX = 40001, gminY = 40001, gmaxX = 0, gmaxY = 0;
        for (int j = 0; j < n; j++) {
            if (point[j][0] < gminX) gminX = point[j][0];
            if (point[j][0] > gmaxX) gmaxX = point[j][0];
            if (point[j][1] < gminY) gminY = point[j][1];
            if (point[j][1] > gmaxY) gmaxY = point[j][1];
        }
        for (int i = 0; i < n; i++) {
            if (point[i][0] == gminX || point[i][0] == gmaxX ||
                    point[i][1] == gminY || point[i][1] == gmaxY) {
                int minX = 40001, minY = 40001, maxX = 0, maxY = 0;
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (point[j][0] < minX) minX = point[j][0];
                    if (point[j][0] > maxX) maxX = point[j][0];
                    if (point[j][1] < minY) minY = point[j][1];
                    if (point[j][1] > maxY) maxY = point[j][1];
                }
                int area = (maxX - minX) * (maxY - minY);
                if (minArea > area) minArea = area;
            }
        }
        out.println(minArea);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("reduce");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}