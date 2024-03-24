package googlecodejam.y22.qual;

import java.io.*;
import java.util.InputMismatchException;

class PunchedCards {
    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        char[][] box = {
                {'+', '-'},
                {'|', '.'}
        };
        for (int test = 1; test <= t; test++) {
            out.printLine("Case #" + test + ": ");
            int R = in.readInt() * 2 + 1;
            int C = in.readInt() * 2 + 1;
            char[][] matrix = new char[R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++) {
                    matrix[i][j] = box[i % 2][j % 2];
                }
            matrix[0][0] = '.';
            matrix[0][1] = '.';
            matrix[1][0] = '.';
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    out.print(matrix[i][j]);
                }
                out.printLine();
            }
        }
        out.close();
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
