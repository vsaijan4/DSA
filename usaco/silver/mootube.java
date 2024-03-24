package usaco.silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mootube {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;
    public static int ans;
    public static int N;
    public static ArrayList<Edge>[] adjList;
    public static boolean[] visited;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++)
            adjList[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bReader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, r));
            adjList[b].add(new Edge(a, r));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bReader.readLine());
            int K = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken()) - 1;
            ans = 0;
            visited = new boolean[N];
            dfs(V, K);
            out.println(ans);
        }
    }

    public static void dfs(int v, int k) {
        visited[v] = true;
        for (Edge e : adjList[v])
            if (!visited[e.dest] && e.rel >= k) {
                ans++;
                dfs(e.dest, k);
            }
    }

    public static class Edge {
        public int dest, rel;

        public Edge(int a, int b) {
            dest = a;
            rel = b;
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("mootube");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}