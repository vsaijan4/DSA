package usaco.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class closing {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;
    static ArrayList<Integer>[] adjList;
    static int[] parent;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            parent[i] = i;
        }
        int[] u = new int[M];
        int[] v = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bReader.readLine());
            u[i] = Integer.parseInt(st.nextToken()) - 1;
            v[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int[] order = new int[N];
        int[] place = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            st = new StringTokenizer(bReader.readLine());
            order[i] = Integer.parseInt(st.nextToken()) - 1;
            place[order[i]] = i;
        }
        for (int i = 0; i < M; i++) {
            if (place[u[i]] < place[v[i]])
                adjList[v[i]].add(u[i]);
            else
                adjList[u[i]].add(v[i]);
        }
        boolean[] ans = new boolean[N];
        int numComps = 0;
        for (int i = 0; i < N; i++) {
            numComps++;
            int a = order[i];
            for (int b : adjList[a]) {
                if (find(a) != find(b)) {
                    union(a, b);
                    numComps--;
                }
            }
            if (numComps == 1) ans[i] = true;
        }
        for (int i = N - 1; i >= 0; i--) {
            if (ans[i])
                out.println("YES");
            else
                out.println("NO");
        }
    }

    static int find(int x) {
        int res = x;
        while (parent[res] != res)
            res = parent[res];
        int m;
        while (parent[x] != x) {
            m = parent[x];
            parent[x] = res;
            x = m;
        }
        return res;
    }

    static void union(int a, int b) {
        int c = find(a);
        int d = find(b);
        if (c != d) parent[d] = c;
    }

    public static void main(String[] args) throws IOException {
        initializeSys("closing");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}