package books.gametheory;

import java.io.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

/**
 * @author Me
 * <p>
 * GameTheory
 * Game of Nim
 * Grundy
 * Mex
 * <p>
 * (MULGAME)
 */

class Grundy {
    public static int[] A;
    public static int[][] ALimits;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int N = in.readInt();
            int Q = in.readInt();
            int M = in.readInt();
            A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = in.readInt();
            }
            ALimits = new int[Q][2];
            for (int i = 0; i < Q; i++) {
                ALimits[i][0] = in.readInt() - 1;
                ALimits[i][1] = in.readInt() - 1;
            }
            int[][] winMatrix = new int[Q][M + 1];
            for (int qIdx = 0; qIdx < Q; qIdx++) {
                int L = ALimits[qIdx][0];
                int R = ALimits[qIdx][1];
                for (int G = M; G >= A[L]; G--) {
                    if (calculateGrundy(G, L, R) != 0)
                        winMatrix[qIdx][G] = 1;
                }
            }
            int maxWins = 0;
            for (int j = 1; j <= M; j++) {
                int count = 0;
                for (int i = 0; i < Q; i++) {
                    if (winMatrix[i][j] == 1)
                        count++;
                }
                if (count > maxWins) maxWins = count;
            }
            out.printLine(maxWins);
        }
        out.close();
    }

    public static int calculateGrundy(int pos, int L, int R) {
        if (pos == 0) return 0;
        Set<Integer> transitionSet = new HashSet<>();
        for (int i = L; i <= R; i++) {
            int transitionValue = pos - A[i];
            if (transitionValue < 0) break;
            transitionSet.add(calculateGrundy(transitionValue, L, R));
        }
        return calculateMex(transitionSet);
    }

    public static int calculateMex(Set<Integer> transitionSet) {
        int mex = 0;
        while (transitionSet.contains(mex))
            mex++;
        return mex;
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