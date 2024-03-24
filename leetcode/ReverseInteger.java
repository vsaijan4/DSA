package leetcode;

import java.io.*;
import java.util.InputMismatchException;

class ReverseInteger {
    final static InputReader in = new InputReader(System.in);
    final static OutputWriter out = new OutputWriter(System.out);

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverseInt(in.readInt()));
        out.close();
    }

    public int reverseInt(int x) {
        StringBuilder sbuild = new StringBuilder();
        if (x == 0) return x;
        if (x == Integer.MIN_VALUE) return 0;
        boolean isNeg = false;
        if (x < 0) {
            isNeg = true;
            x *= -1;
        }
        while (x % 10 == 0) x /= 10;
        while (x != 0) {
            sbuild.append(x % 10);
            x /= 10;
        }
        int ans = 0;
        int len = sbuild.length();
        String str = sbuild.toString();
        if (len < 10) {
            ans = Integer.parseInt(str);
            if (isNeg) ans *= -1;
        } else if (len == 10) {
            if (isNeg) {
                if ("2147483648".compareTo(str) > -1)
                    ans = Integer.parseInt(str) * -1;

            } else {
                if ("2147483647".compareTo(str) > -1)
                    ans = Integer.parseInt(str);
            }
        }
        return ans;
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        protected InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
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

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
