package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class lostcow {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dist = 0;
        int p = 1;
        y -= x;
        while (true) {
            if (y >= 0 && y <= p) {
                dist += y;
                break;
            }
            dist += 2 * p;
            p <<= 1;
            if (y <= 0 && y >= p * -1) {
                dist += y * -1;
                break;
            }
            dist += 2 * p;
            p <<= 1;
        }
        out.println(dist);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("lostcow");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}