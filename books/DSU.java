package books;

public class DSU {
    static int[] parent;
    static int N = 10;

    public static void main(String[] args) {
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
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

    static int findIterative(int x) {
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
}
