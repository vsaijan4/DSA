package googlecodejam.y21.qual;

import java.io.*;
import java.util.InputMismatchException;

class Reversort {
    public static int[] arr;
    public static int n;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            n = in.readInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.readInt();
            }
            int cost = 0;
            for (int i = 0; i < n - 1; i++) {
                int j = minIdx(i, n - 1);
                reverse(i, j);
                cost += j - i + 1;
                //out.printLine(Arrays.toString(arr));
            }
            out.printLine("Case #" + test + ": " + cost);
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

    public static int minIdx(int l, int r) {
        int min = arr[l];
        int minIdx = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIdx = i;
            }
        }
        return minIdx;
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
