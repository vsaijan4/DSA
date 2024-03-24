package googlecodejam.y22.qual;

import java.io.*;
import java.util.*;

public class ChainReactions {
    public static final int MAX_FUN_VALUE = 1_000_000_001;
    public static HashMap<Integer, ArrayList<Integer>> childrenMap;
    public static int[] funFactors;
    public static long sum;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        for (int test = 1; test <= t; test++) {
            sum = 0;
            int N = in.readInt();
            funFactors = new int[N];
            int[] points = new int[N];
            childrenMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                funFactors[i] = in.readInt();
            }
            for (int i = 0; i < N; i++) {
                points[i] = in.readInt() - 1;
            }
            createChildrenMap(points);
            ArrayList<Integer> childList = childrenMap.get(-1);
            for (Integer childId : childList) {
                setOptimalFunFactor(childId);
                sum += funFactors[childId];
            }
            out.printLine("Case #" + test + ": " + sum);
        }
        out.close();
    }

    public static void setOptimalFunFactor(int parentId) {
        if (!childrenMap.containsKey(parentId)) {
            return;
        }
        ArrayList<Integer> childList = childrenMap.get(parentId);
        int minFunValue = MAX_FUN_VALUE;
        int minChildId = 0;
        for (Integer childId : childList) {
            setOptimalFunFactor(childId);
            if (funFactors[childId] < minFunValue) {
                minFunValue = funFactors[childId];
                minChildId = childId;
            }
            sum += funFactors[childId];
        }
        sum -= minFunValue;
        if (funFactors[parentId] < funFactors[minChildId]) {
            funFactors[parentId] = funFactors[minChildId];
        }
    }

    public static void createChildrenMap(int[] points) {
        int N = points.length;
        for (int i = 0; i < N; i++) {
            int point = points[i];
            if (childrenMap.containsKey(point)) {
                childrenMap.get(point).add(i);
            } else {
                ArrayList<Integer> childList = new ArrayList<>();
                childList.add(i);
                childrenMap.put(point, childList);
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