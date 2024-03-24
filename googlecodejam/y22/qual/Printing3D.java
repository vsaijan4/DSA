package googlecodejam.y22.qual;

import java.io.*;
import java.util.InputMismatchException;

public class Printing3D {
    public static final int INK_NEEDED = 1_000_000;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        int[][] ink = new int[3][4];
        for (int test = 1; test <= t; test++) {
            out.print("Case #" + test + ": ");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    ink[i][j] = in.readInt();
                }
            }
            int[] min = new int[4];
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                min[j] = Math.min(Math.min(ink[0][j], ink[1][j]), ink[2][j]);
                sum += min[j];
            }
            if (sum < INK_NEEDED) {
                out.print("IMPOSSIBLE\n");
                continue;
            }
            sum = INK_NEEDED;
            for (int i = 0; i < 3; i++) {
                if (sum >= min[i]) {
                    out.print(min[i] + " ");
                    sum -= min[i];
                } else {
                    out.print(sum + " ");
                    sum = 0;
                }
            }
            out.print(sum);
            if (test + 1 <= t) out.printLine();
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
