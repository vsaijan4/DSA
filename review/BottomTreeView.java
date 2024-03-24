package review;

import java.util.*;

class Tree {
    int val;
    int pos;
    Tree left, right;

    Tree(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BottomTreeView {
    public static void main(String[] args) {
        Tree one = new Tree(1);
        Tree two = new Tree(2);
        Tree three = new Tree(3);
        Tree four = new Tree(4);
        Tree five = new Tree(5);
        Tree six = new Tree(6);
        Tree seven = new Tree(7);
        Tree eight = new Tree(8);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.right = eight;
        eight.left = six;
        eight.right = seven;
        System.out.println(bottomView(one));
    }

    public static ArrayList<Integer> bottomView(Tree root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        LinkedList<Tree> queue = new LinkedList<>();
        root.pos = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            Tree node = queue.removeFirst();
            treeMap.put(node.pos, node.val);
            if (node.left != null) {
                node.left.pos = node.pos - 1;
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.pos = node.pos + 1;
                queue.add(node.right);
            }
        }
        for (Map.Entry<Integer, Integer> mapEntry : treeMap.entrySet()) {
            ans.add(mapEntry.getValue());
        }
        return ans;
    }
}
