package books;

import java.io.*;
import java.util.*;

/**
 * @author Me
 * <p>
 * DFS
 * HashMap
 * FactorPowers
 * Sieve
 * (ATWNT)
 */

class TreeDFSMap {
    public static HashMap<Integer, ArrayList<Integer>> map;
    public static HashMap<Integer, HashMap<Integer, Integer>> nodeWithFactorPowers;
    public static int failed;
    public static int[] childrenCount;
    public static final int MAX = 1000001;
    public static int[] prime = new int[MAX];

    public static void main(String[] args) {
        final InputReader in = new InputReader(System.in);
        final OutputWriter out = new OutputWriter(System.out);
        sieve();
        int n = in.readInt();
        map = new HashMap<>();
        nodeWithFactorPowers = new HashMap<>();
        int child, parent;
        childrenCount = new int[n + 1];
        for (child = 2; child <= n; child++) {
            parent = in.readInt();
            if (!map.containsKey(parent)) {
                ArrayList<Integer> childList = new ArrayList<>();
                childList.add(child);
                map.put(parent, childList);
            } else {
                map.get(parent).add(child);
            }
            childrenCount[parent]++;
        }
        int q = in.readInt();
        int[] queryNode = new int[q];
        int[] queryTasks = new int[q];
        for (int i = 0; i < q; i++) {
            queryNode[i] = in.readInt();
            factorPowers(queryNode[i]);
            queryTasks[i] = in.readInt();
        }
        for (int i = 0; i < q; i++) {
            failed = 0;
            HashMap<Integer, Integer> taskFactorPower = factorPowerGenerator(queryTasks[i]);
            dfs(queryNode[i], queryTasks[i], taskFactorPower);
            out.printLine(failed);
        }
        out.close();
    }

    public static void factorPowers(int node) {
        if (map.containsKey(node) && !nodeWithFactorPowers.containsKey(node)) {
            List<HashMap<Integer, Integer>> list = new ArrayList<>();
            for (int child : map.get(node)) {
                if (map.containsKey(child)) {
                    if (!nodeWithFactorPowers.containsKey(child))
                        nodeWithFactorPowers.put(child, factorPowerGenerator(childrenCount[child]));
                    list.add(nodeWithFactorPowers.get(child));
                }
            }
            nodeWithFactorPowers.put(node, LCMOfFactorPowers(list));
        }
    }

    public static HashMap<Integer, Integer> factorPowerGenerator(int value) {
        HashMap<Integer, Integer> factorPowerMap = new HashMap<>();
        while (value > 1) {
            int factor = prime[value];
            if (factorPowerMap.containsKey(factor))
                factorPowerMap.put(factor, factorPowerMap.get(factor) + 1);
            else
                factorPowerMap.put(factor, 1);
            value /= factor;
        }
        return factorPowerMap;
    }

    public static void sieve() {
        prime[0] = 1;
        prime[1] = 1;
        for (int i = 2; i < MAX; i++) {
            if (prime[i] == 0) {
                for (int j = i * 2; j < MAX; j += i) {
                    if (prime[j] == 0)
                        prime[j] = i;
                }
                prime[i] = i;
            }
        }
    }

    public static HashMap<Integer, Integer> LCMOfFactorPowers(List<HashMap<Integer, Integer>> list) {
        HashMap<Integer, Integer> maxMap = new HashMap<>();
        list.forEach(map -> map.forEach((key, value) -> {
            if (maxMap.containsKey(key)) {
                int maxPower = Math.max(value, maxMap.get(key));
                maxMap.put(key, maxPower);
            } else {
                maxMap.put(key, value);
            }
        }));
        return maxMap;
    }

    public static void dfs(int node, int tasks, HashMap<Integer, Integer> taskFactorPower) {
        int numChildren = childrenCount[node];
        if (numChildren == 0)
            return;
        if (tasks % numChildren != 0) {
            failed += tasks;
            return;
        }
        tasks /= numChildren;
        divideTasksByChildrenCount(numChildren, taskFactorPower);
        if (isTaskSufficient(node, taskFactorPower))
            return;
        for (int child : map.get(node)) {
            dfs(child, tasks, taskFactorPower);
        }
    }

    public static void divideTasksByChildrenCount(int numChildren, HashMap<Integer, Integer> taskFactorPower) {
        HashMap<Integer, Integer> numChildrenFactorPower = factorPowerGenerator(numChildren);
        numChildrenFactorPower.forEach((key, value) -> taskFactorPower.put(key, taskFactorPower.get(key) - value));
    }

    public static boolean isTaskSufficient(int node, HashMap<Integer, Integer> taskFactorPower) {
        HashMap<Integer, Integer> nodeFactorPower = nodeWithFactorPowers.get(node);
        for (Map.Entry<Integer, Integer> factor : nodeFactorPower.entrySet()) {
            if (!taskFactorPower.containsKey(factor.getKey()) || taskFactorPower.get(factor.getKey()) < factor.getValue())
                return false;
        }
        return true;
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
