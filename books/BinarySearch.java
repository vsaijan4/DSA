package books;

class BinarySearch {
    static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] == target)
                return m;
            if (arr[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int x = 10;
        int result = binarySearch(arr, x);
        if (result == -1)
            System.out.println("-1");
        else
            System.out.println(result);
    }
}
