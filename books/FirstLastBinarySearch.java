package books;

import java.util.Arrays;

/*
    Find First and Last Position of Element in Sorted Array
    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
class FirstLastBinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 7};
        int x = 15;
        System.out.println(Arrays.toString(searchRange(arr, x)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int index = binary(nums, target);
        if (index == -1) return ans;
        ans[0] = binaryleft(nums, target, index);
        ans[1] = binaryRight(nums, target, index);
        return ans;
    }

    public static int binary(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    public static int binaryleft(int[] nums, int target, int index) {
        int l = 0;
        int r = index;
        int mid;
        int pos = index;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                pos = mid;
                r = mid - 1;
            } else {            // nums[mid] < target
                l = mid + 1;
            }
        }
        return pos;
    }

    public static int binaryRight(int[] nums, int target, int index) {
        int l = index;
        int r = nums.length - 1;
        int mid;
        int pos = index;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                pos = mid;
                l = mid + 1;
            } else {            // nums[mid] > target
                r = mid - 1;
            }
        }
        return pos;
    }
}