package usaco.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class helpcross {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    static class Time implements Comparable<Time> {
        int start, end;

        public Time(int s, int e) {
            start = s;
            end = e;
        }

        public int compareTo(Time p) {
            if (end == p.end)
                return Integer.compare(start, p.start);
            return Integer.compare(end, p.end);
        }
    }

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] chick = new int[c];
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(bReader.readLine());
            chick[i] = Integer.parseInt(st.nextToken());
        }
        Time[] cow = new Time[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            cow[i] = new Time(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(chick);
        Arrays.sort(cow);
        int ans = 0;
        boolean[] used = new boolean[c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                if (cow[i].end < chick[j]) {
                    break;
                }
                if (!used[j] && chick[j] >= cow[i].start) {
                    ans++;
                    used[j] = true;
                    break;
                }
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("helpcross");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}