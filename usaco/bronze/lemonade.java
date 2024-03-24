package usaco.bronze;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lemonade {
    public static BufferedReader bReader;
    public static PrintWriter out;
    public static StringTokenizer st;

    public static void solve() throws IOException {
        st = new StringTokenizer(bReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(bReader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        int i = 0, ans = 0, cnt = 0;
        int j = n - 1;
        while (i <= j) {
            if (a[i] >= cnt) {
                ans++;
                cnt++;
                j--;
            } else {
                i++;
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initializeSys("lemonade");
        solve();
        out.close();
    }

    public static void initializeSys(String fileName) throws IOException {
        bReader = new BufferedReader(new FileReader(fileName + ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
    }
}