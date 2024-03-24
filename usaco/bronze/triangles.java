package usaco.bronze;

import java.io.*;
import java.util.StringTokenizer;

class triangles {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bReader.readLine());
            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
        }
        int maxArea = 0;
        int area;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    area = 0;
                    if (p[i][0] == p[j][0]) {
                        if (p[i][1] == p[k][1]) {
                            area = Math.abs(p[i][1] - p[j][1]) * Math.abs(p[i][0] - p[k][0]);
                        } else if (p[j][1] == p[k][1]) {
                            area = Math.abs(p[i][1] - p[j][1]) * Math.abs(p[j][0] - p[k][0]);
                        }
                    } else if (p[i][1] == p[j][1]) {
                        if (p[i][0] == p[k][0]) {
                            area = Math.abs(p[i][0] - p[j][0]) * Math.abs(p[i][1] - p[k][1]);
                        } else if (p[j][0] == p[k][0]) {
                            area = Math.abs(p[i][0] - p[j][0]) * Math.abs(p[j][1] - p[k][1]);
                        }
                    } else if (p[i][0] == p[k][0] && p[j][1] == p[k][1]) {
                        area = Math.abs(p[i][1] - p[k][1]) * Math.abs(p[j][0] - p[k][0]);
                    } else if (p[i][1] == p[k][1] && p[j][0] == p[k][0]) {
                        area = Math.abs(p[i][0] - p[k][0]) * Math.abs(p[j][1] - p[k][1]);
                    }
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        out.println(maxArea);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("triangles");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}