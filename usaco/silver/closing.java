package usaco.silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class closing {
    static BufferedReader bReader;
    static PrintWriter out;
    static StringTokenizer st;
    static ArrayList<Integer> vertexList;
    static ArrayList<Integer>[] edgeList;
    static boolean[] visited;
    static int VERTEX_SIZE;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        vertexList = new ArrayList<>(N);
        edgeList = new ArrayList[N];
        VERTEX_SIZE = N;
        for (int i = 0; i < N; i++) {
            vertexList.add(i);
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bReader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edgeList[a].add(b);
            edgeList[b].add(a);
        }
        while (VERTEX_SIZE > 0) {
            visited = new boolean[N];
            int size = dfs(vertexList.get(0));
            if (size == VERTEX_SIZE)
                out.println("YES");
            else
                out.println("NO");
            st = new StringTokenizer(bReader.readLine());
            int rm = Integer.parseInt(st.nextToken()) - 1;
            removeVertex(rm);
        }
    }

    static int dfs(int node) {
        if (visited[node]) return 0;
        visited[node] = true;
        int size = 1;
        for (int i : edgeList[node])
            size += dfs(i);
        return size;
    }

    static void removeVertex(int v) {
        for (int i : edgeList[v])
            edgeList[i].remove((Integer) v);
        vertexList.remove((Integer) v);
        VERTEX_SIZE--;
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