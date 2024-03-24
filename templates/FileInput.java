package templates;

import java.io.*;

public class FileInput {
    static BufferedReader bReader;
    static PrintWriter out;

    public static void solve() throws IOException {
        String st = bReader.readLine();
        int N = Integer.parseInt(st);
        for (int i = 0; i < N; i++) {
            st = bReader.readLine();
            out.println(st);
        }
    }

    public static void main(String[] args) throws IOException {
        initializeSys("sample");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}
