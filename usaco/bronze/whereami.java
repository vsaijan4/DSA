package usaco.bronze;
import java.io.*;
import java.util.StringTokenizer;

public class whereami {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bReader.readLine());
        String str = st.nextToken();
        int len = 1;
        for (; len <= n; len++) {
            boolean repeat = false;
            for (int j = 0; j + len <= n; j++) {
                String a = str.substring(j, j + len);
                for (int k = j + 1; k + len <= n; k++) {
                    String b = str.substring(k, k + len);
                    if (a.equals(b)) {
                        repeat = true;
                        break;
                    }
                }
                if (repeat)
                    break;
            }
            if (!repeat)
                break;
        }
        out.println(len);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("whereami");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}