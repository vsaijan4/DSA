package googlecodejam.y20.round1a;

import java.io.*;

class PatternMatching {
    public static void main(String[] args) throws IOException {
        final OutputWriter out = new OutputWriter(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            int n = Integer.parseInt(br.readLine());
            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = br.readLine();
            }
            int smax = 0;
            int smin = 0;
            int indexMax = 0;
            int indexMin = 100;
            for (int si = 0; si < n; si++) {
                int firstIndex = s[si].indexOf('*');
                if (firstIndex > indexMax) {
                    smax = si;
                    indexMax = firstIndex;
                }
                int lastIndex = s[si].lastIndexOf('*');
                if (s[si].length() - lastIndex > s[smin].length() - indexMin) {
                    smin = si;
                    indexMin = lastIndex;
                }
            }
            boolean fail = false;
            String prefix = s[smax].substring(0, indexMax);
            int prefixLength = prefix.length();
            for (int i = 0; i < n; i++) {
                if (!isSubstring(prefix, prefixLength, s[i].substring(0, s[i].indexOf('*')))) {
                    fail = true;
                    break;
                }
            }
            if (fail) {
                out.printLine("Case #" + test + ": *");
                continue;
            }
            String suffix = s[smin].substring(indexMin + 1);
            int suffixLength = suffix.length();
            for (int i = 0; i < n; i++) {
                if (!isReverseSubstring(suffix, suffixLength, s[i].substring(s[i].lastIndexOf('*') + 1))) {
                    fail = true;
                    break;
                }
            }
            if (fail) {
                out.printLine("Case #" + test + ": *");
                continue;
            }
            StringBuilder sBuf = new StringBuilder();
            sBuf.append(prefix);
            for (int i = 0; i < n; i++) {
                int prevIndex = s[i].indexOf('*');
                int currIndex = s[i].indexOf('*', prevIndex + 1);
                while (currIndex != -1) {
                    sBuf.append(s[i], prevIndex + 1, currIndex);
                    prevIndex = currIndex;
                    currIndex = s[i].indexOf('*', prevIndex + 1);
                }
            }
            sBuf.append(suffix);
            out.printLine("Case #" + test + ": " + sBuf.toString());
        }
        out.close();
    }

    public static boolean isSubstring(String a, int aLen, String b) {
        int bLen = b.length();
        for (int i = 0; i < aLen && i < bLen; i++) {
            if (a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isReverseSubstring(String a, int aLen, String b) {
        int bLen = b.length();
        for (int i = aLen - 1, j = bLen - 1; i >= 0 && j >= 0; i--, j--) {
            if (a.charAt(i) != b.charAt(j))
                return false;
        }
        return true;
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
