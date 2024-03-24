package googlecodejam.y22;

import java.io.*;
import java.util.InputMismatchException;

public class Inflation {
    public static final int LIMIT = 1_000_000_000;
    public static int[] low;
    public static int[] high;
    public static int n;
    public static long ans;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            ans = 0L;
            n = in.readInt();
            int p = in.readInt();
            low = new int[n];
            high = new int[n];

            for (int i = 0; i < n; i++) {
                int ll = LIMIT;
                int hh = 0;
                for (int j = 0; j < p; j++) {
                    int val = in.readInt();
                    if (val < ll) {
                        ll = val;
                    }
                    if (val > hh) {
                        hh = val;
                    }
                }
                low[i] = ll;
                high[i] = hh;
            }
            int curr = 0;
            ans = recursion(0, curr);

            out.printLine("Case #" + test + ": " + ans);
        }
        out.close();
    }

    public static long recursion(int index, int curr) {
        if (index == n) return 0;
        long a = Math.abs(curr - low[index]) + Math.abs(low[index] - high[index]) + recursion(index + 1, high[index]);
        long b = Math.abs(curr - high[index]) + Math.abs(low[index] - high[index]) + recursion(index + 1, low[index]);
        return Math.min(a, b);
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
