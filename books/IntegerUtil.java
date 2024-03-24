package books;

/**
 * @author EgorK
 * <p>
 * BinomialCalculator
 * -Factorial
 * -Reverse Factorial
 * Power
 */

class IntegerUtil {
    static class IntegerUtils {
        public static long power(long base, long exponent, long mod) {
            if (base >= mod) {
                base %= mod;
            }
            if (exponent == 0) {
                return 1 % mod;
            }
            long result = power(base, exponent >> 1, mod);
            result = result * result % mod;
            if ((exponent & 1) != 0) {
                result = result * base % mod;
            }
            return result;
        }

        public static long[] generateFactorial(int count, long module) {
            long[] result = new long[count];
            if (module == -1) {
                if (count != 0) {
                    result[0] = 1;
                }
                for (int i = 1; i < count; i++) {
                    result[i] = result[i - 1] * i;
                }
            } else {
                if (count != 0) {
                    result[0] = 1 % module;
                }
                for (int i = 1; i < count; i++) {
                    result[i] = (result[i - 1] * i) % module;
                }
            }
            return result;
        }

        public static long reverse(long number, long module) {
            return power(number, module - 2, module);
        }

        public static long[] generateReverse(int upTo, long module) {
            long[] result = new long[upTo];
            if (upTo > 1) {
                result[1] = 1;
            }
            for (int i = 2; i < upTo; i++) {
                result[i] = (module - module / i * result[((int) (module % i))] % module) % module;
            }
            return result;
        }

        public static long[] generateReverseFactorials(int upTo, long module) {
            long[] result = generateReverse(upTo, module);
            if (upTo > 0) {
                result[0] = 1;
            }
            for (int i = 1; i < upTo; i++) {
                result[i] = result[i] * result[i - 1] % module;
            }
            return result;
        }

    }

    static class BinomialCalculator {
        private long mod;
        private long[] fact;
        private long[] revFact;

        public BinomialCalculator(int n, long mod) {
            this.mod = mod;
            fact = IntegerUtils.generateFactorial(n + 1, mod);
            revFact = IntegerUtils.generateReverseFactorials(n + 1, mod);
        }

        public long c(int n, int m) {
            if (m < 0 || m > n) {
                return 0;
            }
            return fact[n] * revFact[m] % mod * revFact[n - m] % mod;
        }

    }
}