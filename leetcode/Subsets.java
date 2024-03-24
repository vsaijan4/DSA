package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
    Generate all Subsets using bits
*/

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 2, 3};
        System.out.println(sol.subsets(arr));
    }

    public List<List<Integer>> subsets(int[] nums) {
        int max = 1 << nums.length;
        List<List<Integer>> powerset = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            int j = 1;
            for (int num : nums) {
                if ((i & j) > 0)
                    subset.add(num);
                j <<= 1;
            }
            powerset.add(subset);
        }
        return powerset;
    }
}
