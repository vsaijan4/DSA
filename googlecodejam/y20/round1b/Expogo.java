package googlecodejam.y20.round1b;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

class Expogo {
    public static StringBuilder sbuf;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            int x = in.readInt();
            int y = in.readInt();
            if ((x + y) % 2 == 0) {
                System.out.println("Case #" + test + ": IMPOSSIBLE");
                continue;
            }
            sbuf = new StringBuilder();
            solve(Math.abs(x), Math.abs(y));
            int len = sbuf.length();
            if (x < 0) {
                for (int i = 0; i < len; i++) {
                    if (sbuf.charAt(i) == 'E')
                        sbuf.setCharAt(i, 'W');
                    else if (sbuf.charAt(i) == 'W')
                        sbuf.setCharAt(i, 'E');
                }
            }
            if (y < 0) {
                for (int i = 0; i < len; i++) {
                    if (sbuf.charAt(i) == 'S')
                        sbuf.setCharAt(i, 'N');
                    else if (sbuf.charAt(i) == 'N')
                        sbuf.setCharAt(i, 'S');
                }
            }
            System.out.println("Case #" + test + ": " + sbuf.toString());
        }
    }

    public static void solve(int x, int y) {
        if (x == 0 && y == 1) {
            sbuf.append("N");
            return;
        }
        if (x == 1 && y == 0) {
            sbuf.append("E");
            return;
        }
        if (x % 2 == 1) {
            x /= 2;
            y /= 2;
            if (x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1) {
                x++;
                sbuf.append("W");
            } else {
                sbuf.append("E");
            }
        } else {
            x /= 2;
            y /= 2;
            if (x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1) {
                y++;
                sbuf.append("S");
            } else {
                sbuf.append("N");
            }
        }
        solve(x, y);
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