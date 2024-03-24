package codilitytest;

import java.io.*;
import java.util.*;

public class NumberOfFriends {
    static boolean[] visited;
    static int count, friends;

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        String[] a = {"Andrea:Ben", "C:D", "E:F", "E:E"};
        String[] b = {"Andrea", "Ben", "C", "E"};
        out.printLine(Arrays.toString(numberOfFriends(a, b)));
        out.close();
    }

    public static int[] numberOfFriends(String[] pairs, String[] members) {
        Set<String> people = new HashSet<>();
        HashMap<String, Integer> peopleIdMap = new HashMap<>();
        int id = 0;
        for (String pair : pairs) {
            String[] str = pair.split(":");
            if (!people.contains(str[0])) {
                peopleIdMap.put(str[0], id++);
                people.add(str[0]);
            }
            if (!people.contains(str[1])) {
                peopleIdMap.put(str[1], id++);
                people.add(str[1]);
            }
        }
        count = people.size();
        int[][] adj = new int[count][count];
        for (String pair : pairs) {
            String[] str = pair.split(":");
            adj[peopleIdMap.get(str[0])][peopleIdMap.get(str[1])] = 1;
            adj[peopleIdMap.get(str[1])][peopleIdMap.get(str[0])] = 1;
        }
        int[] ans = new int[members.length];
        id = 0;
        for (String ele : members) {
            visited = new boolean[count];
            friends = 0;
            dfs(adj, peopleIdMap.get(ele));
            ans[id++] = friends - 1;
        }
        return ans;
    }

    public static void dfs(int[][] adj, int personId) {
        if (visited[personId]) return;
        friends++;
        visited[personId] = true;
        for (int i = 0; i < count; i++) {
            if (!visited[i] && adj[personId][i] == 1) {
                dfs(adj, i);
            }
        }
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
