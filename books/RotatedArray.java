package books;

class RotatedArray {
    public static void main(String[] args) {
        int[] a = {8, 12, 20, 22, 24, 26, 1, 3, 5, 7};
        System.out.println(binarySearch(a, 1));
    }

    public static int binarySearch(int[] a, int target) {
        int start = 0, end = a.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target == a[mid])
                return mid;
            if (a[start] <= a[mid]) {
                if (target >= a[start] && target < a[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > a[mid] && target <= a[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
