package books;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class CombinationSum {
    List<List<Integer>> ans;
    LinkedList<Integer> combination;

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        cs.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> l : cs.ans)
            System.out.println(l);
    }

    public void combinationSum(int[] nums, int target) {
        ans = new ArrayList<>();
        combination = new LinkedList<>();
        explore(nums, target, 0);
    }

    public void explore(int[] nums, int target, int start) {
        if (target == 0) {
            ans.add(new LinkedList<>(combination));
            return;
        }
        if (target < 0) return;
        for (int i = start; i < nums.length; i++) {
            combination.addLast(nums[i]);
            explore(nums, target - nums[i], i);
            combination.removeLast();
        }
    }
}
