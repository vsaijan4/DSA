package googlecodejam.y21.qual;

import java.io.*;

class MoonsNUmbrellas {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        final OutputWriter out = new OutputWriter(System.out);
        int x, y;
        for (int test = 1; test <= t; test++) {
            String[] s = br.readLine().split(" ");
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
            String mural = s[2];
            int len = mural.length();
            int cost = 0;
            int i = 0;
            while (i < len && mural.charAt(i) == '?') {
                i++;
            }
            if (i < len) {
                char prev = mural.charAt(i++);
                while (i < len) {
                    while (i < len && (mural.charAt(i) == '?' || mural.charAt(i) == prev)) {
                        i++;
                    }
                    if (i < len) {
                        if (prev == 'C') {
                            cost += x;
                            prev = 'J';
                        } else {
                            cost += y;
                            prev = 'C';
                        }
                        i++;
                    }
                }
            }
            out.printLine("Case #" + test + ": " + cost);
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
