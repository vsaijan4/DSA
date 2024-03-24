package books;

public class BitChecker {
    public static void check(int num) {
        int numBits = (int) (Math.log(num) / Math.log(2)) + 1;
        int checker = 1;
        for (int i = 0; i < numBits; i++) {
            if ((num & checker) != 0) {
                //bit is set
            }
            checker <<= 1;
        }
    }

    public int decimalLength(int a) {
        return ((int) (Math.log10(a)) + 1) / 2;
    }
}
