package books;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Level-Order (or) Breadth-First Traversal
 */
class Tree {

  int val;
  Tree left, right;

  Tree(int val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }
}

public class BFS {

  public static List<List<Integer>> levelOrder(Tree root) {
    List<List<Integer>> ans = new LinkedList<>();
    if (root == null) {
      return ans;
    }
    Queue<Tree> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      ArrayList<Integer> row = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Tree node = queue.poll();
        row.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      ans.add(row);
    }
    return ans;
  }
}
