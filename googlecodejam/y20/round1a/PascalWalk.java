package googlecodejam.y20.round1a;

import java.io.*;
import java.util.InputMismatchException;

public class PascalWalk {
    public static int target;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            System.out.println("Case #" + test + ": ");
            int[] arr = new int[2];
            arr[0] = 1;
            target = in.readInt() - 1;
            System.out.println("1 1");
            pascalRow(arr);
        }
    }

    public static void pascalRow(int[] arr) {
        int size = arr.length;
        int[] kRow = new int[size + 1];
        kRow[0] = 1;
        for (int i = 1; i < size; i++) {
            kRow[i] = arr[i] + arr[i - 1];
        }
        int middle = (size + 1) / 2;
        int sum = 0;
        for (int i = 0; i < middle; i++) {
            sum += kRow[i];
        }
        if (target >= sum) {
            target -= kRow[middle - 1];
            System.out.println((kRow[1] + 1) + " " + middle);
            pascalRow(kRow);
        } else {
            return;
        }
        if (target == 0)
            return;
        for (int i = middle - 2; i > 0; i--) {
            target -= kRow[i];
            System.out.println((kRow[1] + 1) + " " + (i + 1));
        }
        int k = kRow[1] + 1;
        while (target - k >= 0) {
            target -= k;
            System.out.println((k + 1) + " " + 2);
            k++;
        }
        k--;
        while (target > 0) {
            target--;
            System.out.println((k + 1) + " " + 1);
            k++;
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

        public long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
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

}