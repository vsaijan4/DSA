package usaco.silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class milkvisits {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;
    static ArrayList<Integer>[] adjList;
    static boolean[] isG;
    static boolean[] visited;
    static int[] parent;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        isG = new boolean[N];
        st = new StringTokenizer(bReader.readLine());
        String breed = st.nextToken();
        adjList = new ArrayList[N];
        parent = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            parent[i] = i;
            if (breed.charAt(i) == 'G')
                isG[i] = true;
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(bReader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }
        for (int i = 0; i < N; i++) {
            dfs(i, isG[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bReader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            boolean preferG = false;
            if (st.nextToken().equals("G"))
                preferG = true;
            if (find(a) == find(b) && isG[a] != preferG) {
                sb.append("0");
            } else {
                sb.append("1");
            }
        }
        out.println(sb.toString());
    }

    static void dfs(int node, boolean isCompG) {
        if (visited[node]) return;
        visited[node] = true;
        for (int v : adjList[node]) {
            if (isG[v] == isCompG) {
                union(node, v);
                dfs(v, isCompG);
            }
        }
    }

    static void union(int a, int b) {
        int c = find(a);
        int d = find(b);
        if (c != d) parent[d] = c;
    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("milkvisits");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}