package books;

public class Permutation {

    static void generate(int[] arr, int k) {
        if (k == 1) {
            check(arr); // check the current permutation for validity
        } else {
            generate(arr, k - 1);
            for (int i = 0; i < k - 1; i++) {
                if (k % 2 == 0) {
                    swap(arr, i, k - 1);
                    // swap indices i and k-1 of arr
                } else {
                    swap(arr, 0, k - 1);
                    // swap indices 0 and k-1 of arr
                }
                generate(arr, k - 1);
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void check(int[] arr) {

    }
}
