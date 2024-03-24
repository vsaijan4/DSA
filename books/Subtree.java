package books;

import java.io.*;
import java.util.*;

/**
 * @author CS Academy
 * <p>
 * Tree
 * SubTree
 * Power
 * Inverse factorial
 * <p>
 * Source:
 * https://codeforces.com/blog/entry/75627
 * https://csacademy.com/contest/round-11/task/connected-tree-subgraphs/solution/
 */

class Subtree {
    public static final int kMod = 1000000007;
    public static int[] inv_number;
    public static int[] fact;
    public static boolean[] visited;
    public static int[] subtree_size;
    public static int n;
    public static HashMap<Integer, ArrayList<Integer>> neighbour;

    public static int answer = 0;

    public static void Solve(int node, int sum) {
        answer += sum;
        visited[node] = true;
        if (neighbour.containsKey(node)) {
            for (int itr : neighbour.get(node)) {
                if (visited[itr]) {
                    continue;
                }
                Solve(itr, sum * subtree_size[itr] % kMod * inv_number[(n - subtree_size[itr])] % kMod);
            }
        }
        visited[node] = false;
    }

    public static void computeSubtree(int node) {
        visited[node] = true;
        if (neighbour.containsKey(node)) {
            for (int itr : neighbour.get(node)) {
                if (visited[itr]) {
                    continue;
                }
                computeSubtree(itr);
                subtree_size[node] += subtree_size[itr];
            }
        }
        subtree_size[node] += 1;
        visited[node] = false;
    }

    public static void initialise() {
        fact[0] = fact[1] = 1;
        for (int i = 2; i <= n; i += 1) {
            fact[i] = (i * fact[i - 1]) % kMod;
        }
        int inv_fact = Pow(fact[n], kMod - 2);
        for (int i = n; i > 0; i--) {
            inv_number[i] = inv_fact * fact[i - 1] % kMod;
            inv_fact = inv_fact * (i) % kMod;
        }
        inv_number[0] = 1;
    }

    public static int Pow(int a, int p) {
        int res = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                res = (res * a) % kMod;
            }
            p <<= 1;
            a = (a * a) % kMod;
        }
        return res;
    }

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        visited = new boolean[n];
        subtree_size = new int[n];
        inv_number = new int[n + 1];
        fact = new int[n + 1];
        initialise();
        neighbour = new HashMap<>();
        int a, b;
        ArrayList<Integer> childList;
        for (int i = 1; i < n; i++) {
            a = in.readInt() - 1;
            b = in.readInt() - 1;
            if (!neighbour.containsKey(a)) {
                childList = new ArrayList<>();
                childList.add(b);
                neighbour.put(a, childList);
            } else {
                neighbour.get(a).add(b);
            }
            if (!neighbour.containsKey(b)) {
                childList = new ArrayList<>();
                childList.add(a);
                neighbour.put(b, childList);
            } else {
                neighbour.get(b).add(a);
            }
        }

        int root = 0;
        computeSubtree(root);
        int root_answer = fact[n];
        for (int i = 0; i < n; i++) {
            root_answer = (int) ((long) root_answer * inv_number[subtree_size[i]] % kMod);
        }
        Solve(root, root_answer);
        out.printLine(answer % kMod);
        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    outputStream)));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }
    }
}





