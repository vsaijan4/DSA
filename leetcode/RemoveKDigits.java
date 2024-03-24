package leetcode;

import java.io.*;
import java.util.InputMismatchException;

public class RemoveKDigits {
    static final OutputWriter out = new OutputWriter(System.out);

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        String s = in.readString();
        int k = in.readInt();
        RemoveKDigits rk = new RemoveKDigits();
        out.printLine(rk.removeKDigits(s, k));
        out.close();
    }

    public String removeKDigits(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        while (k > 0) {
            int len = sb.length();
            if (k-- >= len) return "0";
            int i = 0;
            while (i + 1 < len && sb.charAt(i) <= sb.charAt(i + 1))
                i++;
            sb.deleteCharAt(i);
            len--;
            i = 0;
            while (i < len && sb.charAt(i) == '0')
                i++;
            if (i > 0)
                sb.delete(0, i);
        }
        if (sb.length() == 0) return "0";
        return sb.toString();
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
