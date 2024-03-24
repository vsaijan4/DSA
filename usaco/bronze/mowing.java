package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class mowing {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int steps;
        String direction;
        int[][] matrix = new int[2001][2001];
        for (int i = 0; i < 2001; i++) {
            for (int j = 0; j < 2001; j++) {
                matrix[i][j] = -1;
            }
        }
        int x = 1000, y = 1000;
        matrix[x][y] = 0;
        int maxTime = 1001;
        int time = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            direction = st.nextToken();
            steps = Integer.parseInt(st.nextToken());
            switch (direction) {
                case "N":
                    for (int j = 0; j < steps; j++) {
                        time++;
                        y++;
                        if (matrix[x][y] != -1 && maxTime > time - matrix[x][y]) {
                            maxTime = time - matrix[x][y];
                        }
                        matrix[x][y] = time;
                    }
                    break;
                case "S":
                    for (int j = 0; j < steps; j++) {
                        time++;
                        y--;
                        if (matrix[x][y] != -1 && maxTime > time - matrix[x][y]) {
                            maxTime = time - matrix[x][y];
                        }
                        matrix[x][y] = time;
                    }
                    break;
                case "W":
                    for (int j = 0; j < steps; j++) {
                        time++;
                        x--;
                        if (matrix[x][y] != -1 && maxTime > time - matrix[x][y]) {
                            maxTime = time - matrix[x][y];
                        }
                        matrix[x][y] = time;
                    }
                    break;
                default:
                    for (int j = 0; j < steps; j++) {
                        time++;
                        x++;
                        if (matrix[x][y] != -1 && maxTime > time - matrix[x][y]) {
                            maxTime = time - matrix[x][y];
                        }
                        matrix[x][y] = time;
                    }
                    break;
            }
        }
        if (maxTime == 1001) {
            maxTime = -1;
        }
        out.println(maxTime);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("mowing");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}