package books;

import java.io.*;
import java.util.InputMismatchException;

/**
 * @author Me
 * <p>
 * SparseTable
 * PrefixCount
 * rangeAND
 * rangeOR
 * (LCKDSAFE)
 **/

class SparseTable {
    public static int N;
    public static int bitsCount = 20;
    public static int[][] prefixCount;
    public static int[][] lookup;

    public static void generatePrefixCount(int[] arr, int n) {
        for (int i = 0; i < bitsCount; i++) {
            prefixCount[i][0] = ((arr[0] >> i) & 1);
            for (int j = 1; j < n; j++) {
                prefixCount[i][j] = ((arr[j] >> i) & 1);
                prefixCount[i][j] += prefixCount[i][j - 1];
            }
        }
    }

    public static int rangeAND(int l, int r) {
        int ans = 0;
        for (int i = 0; i < bitsCount; i++) {
            int x;
            if (l == 0)
                x = prefixCount[i][r];
            else
                x = prefixCount[i][r] - prefixCount[i][l - 1];
            if (x == r - l + 1)
                ans = (ans | (1 << i));
        }
        return ans;
    }

    public static int rangeOR(int l, int r) {
        int ans = 0;
        for (int i = 0; i < bitsCount; i++) {
            int x;
            if (l == 0)
                x = prefixCount[i][r];
            else
                x = prefixCount[i][r] - prefixCount[i][l - 1];

            if (x != 0)
                ans = (ans | (1 << i));
        }
        return ans;
    }

    public static void buildSparseTable(int[] arr, int n) {
        for (int i = 0; i < n; i++)
            lookup[i][0] = arr[i];
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; (i + (1 << j) - 1) < n; i++) {
                lookup[i][j] = Math.max(lookup[i][j - 1], lookup[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public static int query(int L, int R) {
        int j = (int) Math.log(R - L + 1);
        return Math.max(lookup[L][j], lookup[R - (1 << j) + 1][j]);
    }

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        int[] arr;
        while (t-- > 0) {
            N = in.readInt();
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = in.readInt();
            }

            prefixCount = new int[bitsCount][N];
            //edit this value to log(MAX)
            lookup = new int[N][20];
            generatePrefixCount(arr, N);
            buildSparseTable(arr, N);
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if ((rangeOR(i, j) ^ rangeAND(i, j)) >= query(i, j)) {
                        ans++;
                    }
                }
            }
            out.printLine(ans);
        }
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
