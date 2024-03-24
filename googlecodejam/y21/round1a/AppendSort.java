package googlecodejam.y21.round1a;

import java.io.*;
import java.util.InputMismatchException;

public class AppendSort {
    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            int n = in.readInt();
            int ans = 0;
            long prev = in.readLong();
            long curr;
            for (int i = 1; i < n; i++) {
                curr = in.readLong();
                if (curr > prev) {
                    prev = curr;
                } else {
                    String a = String.valueOf(prev);
                    StringBuilder b = new StringBuilder(String.valueOf(curr));
                    int alen = a.length();
                    int blen = b.length();
                    if (alen == blen) {
                        b.append("0");
                        ans++;
                    } else {
                        int p = 0;
                        while (p < blen && a.charAt(p) == b.charAt(p)) {
                            p++;
                        }
                        if (p < blen) {
                            if (b.charAt(p) > a.charAt(p)) {
                                while (b.length() < alen) {
                                    b.append("0");
                                    ans++;
                                }
                            } else {
                                while (b.length() <= alen) {
                                    b.append("0");
                                    ans++;
                                }
                            }
                        } else {
                            long val = Long.parseLong(a.substring(p)) + 1;
                            String suffix = String.valueOf(val);
                            ans += suffix.length();
                            b.append(suffix);
                        }
                    }
                    prev = Long.parseLong(b.toString());
                    System.out.println(prev);
                }
            }
            System.out.println("Case #" + test + ": " + ans);
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