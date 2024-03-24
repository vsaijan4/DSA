package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class photo {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] b = new int[n];
        st = new StringTokenizer(bReader.readLine());
        for (int i = 1; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        int[] a = new int[n + 1];
        boolean[] used;
        int maxFirst = b[1];
        for (int i = 1; i < maxFirst; i++) {
            used = new boolean[n + 1];
            used[i] = true;
            a[1] = i;
            int j = 1;
            for (; j < n; j++) {
                int id = b[j] - a[j];
                if (id > 0 && id <= n && !used[id]) {
                    a[j + 1] = id;
                    used[id] = true;
                } else {
                    break;
                }
            }
            if (j == n) {
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.print(a[n]);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("photo");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}