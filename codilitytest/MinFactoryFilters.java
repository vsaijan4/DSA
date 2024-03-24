package codilitytest;

import java.io.*;
import java.util.InputMismatchException;
import java.util.TreeMap;

/*
Each filter reduces the pollution of the factory by half.
Find minimum number of filters needed to reduce the total
pollution by half.
 */

public class MinFactoryFilters {
    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.readInt();
        out.printLine(solution(a));
        out.close();
    }

    //N    [1..30,000]
    //A[i] [0..70,000]
    public static int solution(int[] A) {
        int total = 0;
        TreeMap<Double, Integer> treeMap = new TreeMap<>();
        for (int value : A) {
            total += value;
            double key = 1.0 * value;
            if (treeMap.containsKey(key)) {
                treeMap.replace(key, treeMap.get(key) + 1);
            } else {
                treeMap.put(key, 1);
            }
        }
        int minFilters = 0;
        double target = total * 1.0 / 2;

        while (target > 0.0) {
            double key = treeMap.lastEntry().getKey();
            int count = treeMap.get(key);
            if (count == 1) {
                treeMap.remove(key);
                double halfKey = key / 2.0;
                if (treeMap.containsKey(halfKey)) {
                    treeMap.replace(halfKey, treeMap.get(halfKey) + 1);
                } else {
                    treeMap.put(halfKey, 1);
                }
            } else {
                treeMap.replace(key, treeMap.get(key) - 1);
            }
            target = target - (key / 2);
            minFilters++;
        }
        return minFilters;
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
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

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return sgn * res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return sgn * res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
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
