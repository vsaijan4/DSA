package googlecodejam.y21.qual;

import java.io.*;
import java.util.InputMismatchException;

class ReversortEngg {
    public static int[] arr;
    public static int n;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            n = in.readInt();
            int cost = in.readInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }
            if (cost < n - 1 || cost > (n * (n + 1) / 2 - 1)) {
                out.printLine("Case #" + test + ": IMPOSSIBLE");
                continue;
            }
            cost -= (n - 1);
            for (int i = n - 2; i >= 0; i--) {
                int j = n - 1;
                if (cost >= j - i) {
                    reverse(i, j);
                    cost -= j - i;
                } else {
                    j = cost + i;
                    reverse(i, j);
                    break;
                }
            }
            out.print("Case #" + test + ":");
            for (int i = 0; i < n; i++) {
                out.print(" " + arr[i]);
            }
            out.printLine();
        }
        out.close();
    }

    public static void reverse(int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
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
