package usaco.bronze;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class cowqueue {
    static class Cow implements Comparable<Cow> {
        int arrival, duration;

        public Cow(int a, int d) {
            arrival = a;
            duration = d;
        }

        public int compareTo(Cow p) {
            return Integer.compare(arrival, p.arrival);
        }
    }

    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a, d;
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            a = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(a, d);
        }
        Arrays.sort(cows);
        int finishTime = 0;
        for (int i = 0; i < n; i++) {
            if (cows[i].arrival >= finishTime) {
                finishTime = cows[i].arrival + cows[i].duration;
            } else {
                finishTime += cows[i].duration;
            }
        }
        out.println(finishTime);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("cowqueue");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}