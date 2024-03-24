package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class shuffle {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        int[] id = new int[n];
        int[] index = new int[n];
        st = new StringTokenizer(bReader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken()) - 1;
            index[a[i]] = i;
        }
        st = new StringTokenizer(bReader.readLine());
        for (int i = 0; i < n; i++) {
            id[index[index[index[i]]]] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            out.println(id[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("shuffle");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}