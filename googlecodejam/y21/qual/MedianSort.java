package googlecodejam.y21.qual;

import java.io.*;
import java.util.InputMismatchException;

class Node {
    int index;
    Node left, right;

    public Node(int index) {
        this.index = index;
        this.left = null;
        this.right = null;
    }
}

public class MedianSort {
    static final InputReader in = new InputReader(System.in);

    public static void main(String[] args) {
        int t = in.readInt();
        int n = in.readInt();
        in.readInt();
        while (t-- > 0) {
            System.out.println("1 2 3");
            System.out.flush();
            int median = in.readInt();
            Node root = new Node(median);
            if (median == 1) {
                root.left = new Node(2);
                root.right = new Node(3);
            } else if (median == 2) {
                root.left = new Node(1);
                root.right = new Node(3);
            } else {
                root.left = new Node(1);
                root.right = new Node(2);
            }
            int i = 4;
            while (i <= n) {
                dfs(i, root, true);
                i++;
            }
            printInOrder(root);
            System.out.println();
            System.out.flush();
            int code = in.readInt();
            if (code == -1) break;
        }
    }

    public static void dfs(int target, Node parent, boolean useLeft) {
        System.out.println(target + " " + parent.index + " " + (useLeft ? parent.left.index : parent.right.index));
        System.out.flush();
        int median = in.readInt();
        if (median == parent.index) {
            if (useLeft) {
                // leftChild < parent < target
                if (parent.right == null) {
                    parent.right = new Node(target);
                } else {
                    dfs(target, parent, false);
                }
            } else {
                // target < parent < rightChild
                if (parent.left == null) {
                    parent.left = new Node(target);
                } else {
                    dfs(target, parent, true);
                }
            }
        } else {
            //median = target or child
            if (useLeft) {
                //leftChild < parent
                if (median == target) {
                    // leftChild < target < parent
                    if (parent.left.right == null)
                        parent.left.right = new Node(target);
                    else
                        dfs(target, parent.left, false);
                } else {
                    // target < leftChild < parent
                    if (parent.left.left == null) {
                        parent.left.left = new Node(target);
                    } else {
                        dfs(target, parent.left, true);
                    }
                }
            } else {
                //parent < rightChild
                if (median == target) {
                    // parent < target < rightChild
                    if (parent.right.left == null)
                        parent.right.left = new Node(target);
                    else
                        dfs(target, parent.right, true);
                } else {
                    // parent < rightChild < target
                    if (parent.right.right == null) {
                        parent.right.right = new Node(target);
                    } else {
                        dfs(target, parent.right, false);
                    }
                }
            }
        }
    }

    public static void printInOrder(Node node) {
        if (node == null)
            return;
        printInOrder(node.left);
        System.out.print(node.index + " ");
        printInOrder(node.right);
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

}
