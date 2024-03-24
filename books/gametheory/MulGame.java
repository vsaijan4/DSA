package books.gametheory;

import java.io.*;
import java.util.InputMismatchException;

/**
 * @author Me
 * <p>
 * GameTheory
 * Game of Nim
 * <p>
 * (MULGAME)
 */

class MulGame {
    public static int[] A;
    public static int M;
    public static int[][] winMatrix;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int N = in.readInt();
            int Q = in.readInt();
            M = in.readInt();
            A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = in.readInt();
            }
            winMatrix = new int[Q][M + 1];
            for (int q = 0; q < Q; q++) {
                int L = in.readInt() - 1;
                int R = in.readInt() - 1;
                for (int i = L; i <= R; i++) {
                    if (A[i] <= M)
                        winMatrix[q][A[i]] = 1;
                }
                for (int G = 1; G <= M; G++) {
                    setWins(G, L, R, q);
                }
            }
            long maxWins = 0;
            for (int j = 1; j <= M; j++) {
                long count = 0;
                for (int i = 0; i < Q; i++) {
                    count += winMatrix[i][j];
                }
                if (count > maxWins) maxWins = count;
            }
            out.printLine(maxWins);
        }
        out.close();
    }

    public static void setWins(int pos, int L, int R, int q) {
        if (winMatrix[q][pos] == 0) {
            for (int i = L; i <= R; i++) {
                if (pos + A[i] <= M) {
                    winMatrix[q][pos + A[i]] = 1;
                } else return;
            }
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