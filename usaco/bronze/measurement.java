package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class measurement {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        int ans = 0;
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] data = new int[101][2];
        int day, change;
        String cow;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            day = Integer.parseInt(st.nextToken());
            cow = st.nextToken();
            change = Integer.parseInt(st.nextToken());
            data[day][1] = change;
            if (cow.charAt(0) == 'B') {
                data[day][0] = 0;
            } else if (cow.charAt(0) == 'E') {
                data[day][0] = 1;
            } else {
                data[day][0] = 2;
            }
        }
        int[] milk = new int[3];
        boolean[] poster = new boolean[3];
        for (int i = 0; i < 3; i++) {
            milk[i] = 7;
            poster[i] = true;
        }
        boolean isChanged;
        int maxMilk;
        boolean[] nextPoster;
        for (int i = 1; i < 101; i++) {
            if (data[i][1] != 0) {
                isChanged = false;
                nextPoster = new boolean[3];
                milk[data[i][0]] += data[i][1];
                maxMilk = Math.max(Math.max(milk[0], milk[1]), milk[2]);
                for (int j = 0; j < 3; j++) {
                    if (milk[j] == maxMilk)
                        nextPoster[j] = true;
                }
                for (int j = 0; j < 3; j++) {
                    if (poster[j] != nextPoster[j]) {
                        isChanged = true;
                        poster[j] = nextPoster[j];
                    }
                }
                if (isChanged) ans++;
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("measurement");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}