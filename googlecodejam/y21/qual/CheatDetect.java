package googlecodejam.y21.qual;

import java.io.*;

class CheatDetect {
    public static final int MAX_Q = 10000;
    public static final int MAX_S = 100;
    public static final int NEW_LIMIT = 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        final OutputWriter out = new OutputWriter(System.out);
        for (int test = 1; test <= t; test++) {
            int[] solved = new int[MAX_Q];
            boolean[][] input = new boolean[MAX_S][MAX_Q];
            for (int i = 0; i < MAX_S; i++) {
                String s = br.readLine();
                for (int j = 0; j < MAX_Q; j++)
                    if (s.charAt(i) == '1') {
                        input[i][j] = true;
                        solved[i]++;
                    }
            }

            int[][] matrix = new int[MAX_S][NEW_LIMIT];
            for (int i = 0; i < NEW_LIMIT; i++) {
                int min = 101;
                int idx = 0;
                for (int j = 0; j < MAX_Q; j++)
                    if (solved[j] < min) {
                        min = solved[j];
                        idx = j;
                    }
                solved[idx] = 100;
                for (int r = 0; r < MAX_S; r++) {
                    if (input[r][idx])
                        matrix[r][i] = 1;
                }
            }

            int[] cheaterScore = new int[MAX_S];
            for (int i = 0; i < MAX_S; i++) {
                int j = 0;
                while (j < NEW_LIMIT && matrix[i][j] == 0) {
                    j++;
                }
                j++;
                while (j < NEW_LIMIT) {
                    if (matrix[i][j] == 0)
                        cheaterScore[i]++;
                    j++;
                }
            }
            int maxCheatScore = -1;
            int cheater = 0;
            for (int i = 0; i < MAX_S; i++) {
                if (maxCheatScore < cheaterScore[i]) {
                    maxCheatScore = cheaterScore[i];
                    cheater = i;
                }
            }
            out.printLine("Case #" + test + ": " + (cheater + 1));
        }
        out.close();
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
