package books;

import java.util.*;

/* @GFG
 * Sieve
 * Power
 * LCM
 */

class Sieve {
    final static int MAX = 10000003;
    final static int mod = 1000000007;
    static int[] prime = new int[MAX];

    public static void sieve() {
        prime[0] = 1;
        prime[1] = 1;

        for (int i = 2; i < MAX; i++) {
            if (prime[i] == 0) {
                for (int j = i * 2; j < MAX; j += i) {
                    if (prime[j] == 0) {
                        prime[j] = i;
                    }
                }
                prime[i] = i;
            }
        }
    }

    public static int power(int a, int n) {
        if (n == 0)
            return 1;
        int p = power(a, n / 2) % mod;
        p = (p * p) % mod;
        if ((n & 1) > 0)
            p = (p * a) % mod;

        return p;
    }

    // Function to return the LCM modulo M
    public static long lcmModuloM(int[] arr, int n) {
        HashMap<Integer, Integer> maxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> temp = new HashMap<>();
            int num = arr[i];
            // Temp stores mapping of prime
            // factor to its power for the
            // current element
            while (num > 1) {
                // Factor is the smallest prime
                // factor of num
                int factor = prime[num];
                if (temp.containsKey(factor))
                    temp.put(factor, temp.get(factor) + 1);
                else
                    temp.put(factor, 1);
                num = num / factor;
            }
            for (Map.Entry<Integer, Integer> m : temp.entrySet()) {
                if (maxMap.containsKey(m.getKey())) {
                    int maxPower = Math.max(m.getValue(), maxMap.get(m.getKey()));
                    maxMap.put(m.getKey(), maxPower);
                } else {
                    maxMap.put(m.getKey(), m.getValue());
                }
            }
        }
        long ans = 1;
        for (Map.Entry<Integer, Integer> m : maxMap.entrySet()) {
            // LCM is product of primes to their highest powers modulo M
            ans = (ans * power(m.getKey(), m.getValue()) % mod);
        }
        return ans;
    }

    public static void main(String[] args) {
        sieve();
        int[] arr = new int[]{36, 500, 480, 343};
        int n = arr.length;
        System.out.println(lcmModuloM(arr, n));
    }
}