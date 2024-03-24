package leetcode;

import java.io.*;
import java.util.InputMismatchException;

public class LongestPalinSubstring {
    final static InputReader in = new InputReader(System.in);
    final static OutputWriter out = new OutputWriter(System.out);

    public static void main(String[] args) {
        LongestPalinSubstring lps = new LongestPalinSubstring();
        out.printLine(lps.longestPalinSubstring(in.readString()));
        out.close();
    }

    public String longestPalinSubstring(String s) {
        int[] max = {0, 0};
        int[] curr;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            curr = palindrome(s, len, i);
            if (curr[1] - curr[0] > max[1] - max[0]) {
                max[0] = curr[0];
                max[1] = curr[1];
            }
        }
        return s.substring(max[0], max[1] + 1);
    }

    public int[] palindrome(String s, int len, int mid) {
        int l = mid, r = mid;
        while (l > -1 && r < len && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        l++;
        r--;
        int x = mid, y = mid + 1;
        while (x > -1 && y < len && s.charAt(x) == s.charAt(y)) {
            x--;
            y++;
        }
        x++;
        y--;
        if (r - l >= y - x)
            return new int[]{l, r};
        return new int[]{x, y};
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        public InputReader.SpaceCharFilter filter;

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

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
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