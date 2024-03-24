package books;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;

/**
 * @author Jaynil
 * <p>
 * FFT
 * FFT Inverse
 * Mod Invserse DP
 * Power
 */

public class FFT {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        XORSums solver = new XORSums();
        solver.solve(1, in, out);
        out.close();
    }

    static class XORSums {
        public static int mod = 998244353;
        public static int g = 3;
        public static int root_pw = 1 << 18;
        public static int n_1 = 998240545;
        public static int[] invdp;
        public static int[] gpow;
        public static int[] invgpow;

        public void fft(int[][] a, int[][] b) {
            int n = a.length;
            for (int i = 1, j = 0; i < n; i++) {
                int bit = n >> 1;
                for (; (j & bit) > 0; bit >>= 1)
                    j ^= bit;
                j ^= bit;
                if (i < j) {
                    for (int k = 0; k < 30; k++) {
                        int temp = a[i][k];
                        a[i][k] = a[j][k];
                        a[j][k] = temp;
                        temp = b[i][k];
                        b[i][k] = b[j][k];
                        b[j][k] = temp;
                    }
                }
            }

            for (int len = 2, idx = 1; len <= n; len <<= 1, idx++) {
                int wlen = gpow[idx];
                for (int i = 0; i < n; i += len) {
                    int w = 1;
                    for (int j = 0; j < len / 2; j++) {

                        for (int k = 0; k < 30; k++) {
                            int u = a[i + j][k], v = (int) (1l * a[i + j + len / 2][k] * w % mod);
                            a[i + j][k] = u + v < mod ? u + v : u + v - mod;
                            a[i + j + len / 2][k] = u - v >= 0 ? u - v : u - v + mod;
                            //
                            u = b[i + j][k];
                            v = (int) (1l * b[i + j + len / 2][k] * w % mod);
                            b[i + j][k] = u + v < mod ? u + v : u + v - mod;
                            b[i + j + len / 2][k] = u - v >= 0 ? u - v : u - v + mod;
                        }
                        w = (int) (1l * w * wlen % mod);
                    }
                }
            }
        }

        public void fftinv(int[][] a) {
            int n = a.length;
            for (int i = 1, j = 0; i < n; i++) {
                int bit = n >> 1;
                for (; (j & bit) > 0; bit >>= 1)
                    j ^= bit;
                j ^= bit;
                if (i < j) {
                    for (int k = 0; k < 30; k++) {
                        int temp = a[i][k];
                        a[i][k] = a[j][k];
                        a[j][k] = temp;
                    }
                }
            }

            for (int len = 2, idx = 1; len <= n; len <<= 1, idx++) {
                int wlen = invgpow[idx];
                for (int i = 0; i < n; i += len) {
                    int w = 1;
                    for (int j = 0; j < len / 2; j++) {
                        for (int k = 0; k < 30; k++) {
                            int u = a[i + j][k], v = (int) (1l * a[i + j + len / 2][k] * w % mod);
                            a[i + j][k] = u + v < mod ? u + v : u + v - mod;
                            a[i + j + len / 2][k] = u - v >= 0 ? u - v : u - v + mod;
                        }
                        w = (int) (1l * w * wlen % mod);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < 30; k++) {
                    a[i][k] = (int) (1l * a[i][k] * n_1 % mod);
                }
            }
        }

        public void multiply(int[][] a, int[][] b) {
            fft(a, b);
            for (int i = 0; i < root_pw; i++) {
                for (int k = 0; k < 30; k++) {
                    a[i][k] = (int) ((1l * a[i][k] * b[i][k]) % mod);
                }
            }
            fftinv(a);
        }

        public void ggen() {
            int where = (mod - 1) / 2, invg = (int) Maths.power(g, mod - 2, mod);
            int idx = 0;
            while (where % 2 == 0) {
                idx++;
                gpow[idx] = (int) Maths.power(g, where, mod);
                invgpow[idx] = (int) Maths.power(invg, where, mod);
                where /= 2;
            }
        }

        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.nextInt();
            int[][] aa = new int[root_pw][30];
            int[][] bb = new int[root_pw][30];
            int[] arr = new int[n];
            int[] c = new int[30];
            invdp = new int[n + 1];
            gpow = new int[30];
            invgpow = new int[30];
            ggen();
            Maths.modInverseDP(n, mod, invdp);
            int[] ans = new int[Math.max(root_pw, n)];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                for (int j = 0; j < 30; j++) {
                    if ((arr[i] & (1 << j)) > 0) c[j]++;
                }
            }
            for (int i = 0; i < 30; i++) {
                int x = c[i];
                int y = n - c[i];
//            System.out.println(x + " " + y);
                aa[0][i] = 1;
                bb[0][i] = 1;
                for (int j = 1; j <= x; j++) {
                    aa[j][i] = (int) ((((1l * aa[j - 1][i] * invdp[j]) % mod) * (x - j + 1)) % mod);
                    if ((j - 1) % 2 == 0) aa[j - 1][i] = 0;
                }
                if ((x % 2 == 0)) {
                    aa[x][i] = 0;
                }
                for (int j = 1; j <= y; j++) {
                    bb[j][i] = (int) ((((1l * bb[j - 1][i] * invdp[j]) % mod) * (y - j + 1)) % mod);
                }
            }
            multiply(aa, bb);
            for (int j = 0; j < root_pw; j++) {
                for (int i = 0; i < 30; i++) {
                    ans[j] = (int) (((long) ans[j] + (aa[j][i] * (1l << i)) % mod) % mod);
                }
            }
            for (int i = 1; i <= n; i++) {
                ans[i] = (int) (((long) ans[i] + ans[i - 1]) % mod);
            }
            int q = in.nextInt();
            while (q-- > 0) {
                int t = in.nextInt();
                out.println(ans[t]);
            }
        }

    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 20;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer;
        private int bytesRead;

        public Reader(InputStream x) {
            din = new DataInputStream(x);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() {
            try {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    buffer[0] = -1;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private byte read() {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }

    static class Maths {
        static void modInverseDP(int n, int prime, int[] dp) {

            dp[0] = dp[1] = 1;
            for (int i = 2; i <= n; i++)
                dp[i] = (int) ((1l * dp[prime % i] * (prime - prime / i)) % prime);

        }

        static long power(long x, long y, long p) {
            long res = 1;
            x = x % p;
            while (y > 0) {
                if ((y & 1) == 1)
                    res = (res * x) % p;
                y = y >> 1;
                x = (x * x) % p;
            }
            return res;
        }

        static long power(long x, long y) {
            long res = 1;
            while (y > 0) {
                if ((y & 1) == 1)
                    res = (res * x);
                y = y >> 1;
                x = (x * x);
            }
            return res;
        }
    }
}